package com.alertgeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlertGenerationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertGenerationServiceApplication.class, args);

	}

}
