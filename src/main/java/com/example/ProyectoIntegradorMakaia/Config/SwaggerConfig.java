package com.example.ProyectoIntegradorMakaia.Config;

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
        Docket api() {
            String baseUrl="http://localhost:8080";
            System.out.println(baseUrl);
            return new Docket(DocumentationType.SWAGGER_2).select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo())
                    .host(baseUrl);
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("API para sistemas de vuelos")
                    .description("Esta API RESTful permite gestionar los procesos de envío de una empresa de mensajería express, con tres microservicios que abarcan las funciones de registro de clientes, registro de empleados y gestión de envíos. La API está diseñada para funcionar con dos roles de usuario diferentes: \"admin\" y \"user\", cada uno con distintos niveles de permisos para acceder a las funciones. Los usuarios con rol \"admin\" tienen acceso completo a todas las funciones, mientras que los usuarios con rol \"user\" tienen acceso limitado a ciertas funciones.")
                    .version("1.0.0")
                    .contact(new Contact("Daneiel Espinosa","https://www.linkedin.com/in/danessti/","danestii@gmail.com"))
                    .contact(new Contact("Jorge Enrique Gómez","https://www.linkedin.com/in/jorge-enrique-g%C3%B3mez-buitrago-31b083278/","jorgegomez98125@gmail.com")
                    )
                    .build();
        }

}
