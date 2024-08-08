package com.c1848tjavaangular.domi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/**")
                .build();
    }
    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Rest Domi")
                        .version("1.0")
                        .description("API REST para unir profesionales con clientes.\n" +
                                "\n" +
                                "Los clientes pueden ver todos los profesionales que esten registrados, seleccionar uno o varios utilizando los filtros y viendo la experiencia y puntuacion.\n" +
                                "\n" +
                                "Los profesionales tienen un perfil donde describen todo lo que hacen por medio de testimonios, certificaciones, imágenes, precio y puntuacion.\n" +
                                "\n" +
                                "Cuando el cliente elije un profesional se abre un chat donde coordinan el encuentro.")
                        .termsOfService("http://github.com/DOMI-WEBSITE/Backend")
                        .license(new License().name("Apache 2.5.0").url("http://springdoc.org"))
                )
                .components(
                        new Components()
                                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                )
                );
    }
}
