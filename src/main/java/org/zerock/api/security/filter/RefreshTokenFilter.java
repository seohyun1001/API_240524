package org.zerock.api.security.filter;

import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.api.security.exception.RefreshTokenException;
import org.zerock.api.util.JWTUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {

    private final String refreshToken;

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 요청 url 취득, path에 저장
        String path = request.getRequestURI();

        // 요청 url이 refreshPath가 아니면 필터로 처리하지 끝냄
        if(!path.equals(refreshToken)) {
            log.info("skip refresh token filter---------------");
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Refresh token filter --- run ---------------1");

        // 전송된 JSON에서 accessToken과 refreshToken을 얻어온다
        Map<String, String> tokens = parseRequestJSON(request);

        // access
        String accessToken = tokens.get("accessToken");
        String refreshToken = tokens.get("refreshToken");

        log.info("accessToken : " + accessToken);
        log.info("refreshToken : " + refreshToken);
        
        try {
            checkAccessToken(accessToken);
        } catch (RefreshTokenException refreshTokenException) {
            refreshTokenException.sendREsponseError(response);
        }

        Map<String, Object> refreshClaims = null;

        try {
            refreshClaims = checkRefreshToken(refreshToken);
            log.info(refreshClaims);



        } catch (RefreshTokenException refreshTokenException) {
            refreshTokenException.sendREsponseError(response);
            return;
        }
    }

    // request의 토큰 데이터를 Map<String, String> 으로 변경
    private Map<String, String> parseRequestJSON(HttpServletRequest request) {
        // JSON 데이터를 분석해서 mid, mpw 전달 값을 MAP으로 처리
        try(Reader reader = new InputStreamReader(request.getInputStream())) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Map.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    // 엑세스 토큰이 정상인지 확인하는 메서드
    private void checkAccessToken(String accessToken) {
        try {
            jwtUtil.validateToken(accessToken);
        } catch (ExpiredJwtException e) {
            log.info("Access token has expired");
        } catch (Exception e) {
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_ACCESS);
        }
    }

    //
    private Map<String, Object> checkRefreshToken(String refreshToken) throws RefreshTokenException{
        try {
            Map<String, Object> values = jwtUtil.validateToken(refreshToken);
            return values;
        } catch (ExpiredJwtException expiredJwtException) {
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.OLD_REFRESH);
        } catch (MalformedJwtException malformedJwtException) {
            throw new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
        } catch (Exception exception) {
            new RefreshTokenException(RefreshTokenException.ErrorCase.NO_REFRESH);
        }
        return null;
    }
}
