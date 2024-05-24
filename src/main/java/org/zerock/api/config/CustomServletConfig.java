package org.zerock.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CustomServletConfig implements WebMvcConfigurer {

//     정적 파일 및 폴더 추가
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                // localhost:8080/files/sample.html 요청 URL
                .addResourceHandler("/files/**")
                // 실제 프로젝트의 위치
                .addResourceLocations("classpath:/static/");

//        registry
//                .addResourceHandler("/js/**")
//                .addResourceLocations("/resources/")
//                .addResourceLocations("classpath:/static/js/");
//
//        registry
//                .addResourceHandler("/fonts/**")
//                .addResourceLocations("/resources/")
//                .addResourceLocations("classpath:/static/fonts/");
//
//        registry
//                .addResourceHandler("/css/**")
//                .addResourceLocations("/resources/")
//                .addResourceLocations("classpath:/static/css/");
//
//        registry
//                .addResourceHandler("/assets/**")
//                .addResourceLocations("/resources/")
//                .addResourceLocations("classpath:/static/assets/");

    }

}
