package com.maccari.tenant_mobile_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class TenantMobileBackendApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

        //Injetar os valores do .env no application.properties
        dotenv.entries().forEach(entry ->
            System.setProperty(entry.getKey(), entry.getValue())
        );

		SpringApplication.run(TenantMobileBackendApplication.class, args);
	}

}
