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

}
