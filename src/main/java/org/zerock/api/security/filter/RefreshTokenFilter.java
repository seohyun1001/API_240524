package org.zerock.api.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.api.util.JWTUtil;

import java.io.IOException;

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
    }
}
