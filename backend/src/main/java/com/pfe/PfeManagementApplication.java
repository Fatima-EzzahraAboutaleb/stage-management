package com.pfe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class PfeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfeManagementApplication.class, args);
    }

    /**
     * Configuration Swagger/OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PFE Management API")
                        .version("1.0.0")
                        .description("API REST pour la gestion des stages PFE"));
    }
}
