package com.example.ProyectoIntegradorMakaia.Config.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API para gestión de Sistema de Vuelos")
                .description("Esta API RESTful permite gestionar el Sistema de Reservas de Vuelos proporciona servicios para buscar vuelos, " +
                        "realizar reservas, gestionar usuarios y cooperar con sistemas de aerolíneas.")
                .version("1.0.0")
                .contact(new Contact("Daniel Espinosa", "https://www.linkedin.com/in/danessti/", "danessti@gmail.com"))
                .contact(new Contact("Jorge Gomez", "https://www.linkedin.com/in/jorge-enrique-g%C3%B3mez-buitrago-31b083278/", "jorgegomez98125@gmail.com"))
                .build();
    }

}