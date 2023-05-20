package com.example.lr20190024.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "njt-crm-backend",
                version = "1.0.0",
                description = "CRM API")
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "crm",
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
