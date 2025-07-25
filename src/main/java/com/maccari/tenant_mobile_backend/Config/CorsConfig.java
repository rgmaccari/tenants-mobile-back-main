package com.maccari.tenant_mobile_backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configura as regras de CORS para permitir requisições do front-end.
*/
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") //Aplica as regras para todos os endpoints.
                    .allowedOrigins("http://localhost:5173") //Permite apenas front-ends rodados em "localhost:5173" faça requisições. 
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //Permite os métodos listados.
                    .allowedHeaders("authorization", "tenant", "content-type", "accept") //Aponta os dados possíveis no header.
                    .exposedHeaders("authorization", "tenant") //Permite que apenas esses cabeçalhos da resposta sejam lidos pelo front-end.
                    .allowCredentials(true); //Permite o envio de cookies ou tokens de autenticação juntamente com a requisição.
            }
        };
    }
}