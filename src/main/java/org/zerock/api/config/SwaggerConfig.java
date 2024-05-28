package org.zerock.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    //  @Bean
//  public GroupedOpenApi publicApi() {
//    return GroupedOpenApi.builder()
//        .group("springshop-public")
//        .pathsToMatch("/public/**")
//        .build();
//  }

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth",securityScheme))
                .security(Arrays.asList(securityRequirement))
                .info(new Info()
                        .title("레스트 API 테스트 ")
                        .description("댓글을 이용해서  REST 방식 테스트 하겠다.")
                        .version("1.0.0"));
    }



}
