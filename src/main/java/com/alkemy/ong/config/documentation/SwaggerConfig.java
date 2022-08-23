package com.alkemy.ong.config.documentation;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Somos Más API")
                        .description("API REST made with springboot")
                        .version("v1.0")
                        .license(new License().name("Alkemy").url("https://www.alkemy.org/")))
                .externalDocs(new ExternalDocumentation()
                        .description("API REST Repository")
                        .url("https://github.com/alkemyTech/OT251-Server"));
    }
}
