package org.zerock.api.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Log4j2
public class JWTUtilTests {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testGenerate() {
        Map<String, Object> cliamMap = Map.of("mid", "ABCDE");
        String jwtStr = jwtUtil.generateToken(cliamMap, 1);
        log.info(jwtStr);
    }

    @Test
    public void testValidate(){
        // 유효시간이 지난 토큰(ExpiredJwtException), 문자열에 임의 문자 추가(SignatureException)해서 오류확인하기
        String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTY4NTg2MjQsIm1pZCI6IkFCQ0RFIiwiaWF0IjoxNzE2ODU4NTY0fQ.QnrnSEDPbsbhClJVnyWpsc9OWOpfrB06om49JVgAkoQ";

        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
        log.info(claim);
    }

    @Test
    public void testAll(){
        String jwtStr = jwtUtil.generateToken(Map.of("mid","AAAAA","email","aaaa@bbb.com"),1);
        log.info(jwtStr);
        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);
        log.info("MID : "+claim.get("mid"));
        log.info("EMAIL : "+claim.get("email"));
    }

}
