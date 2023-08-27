package com.alertmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlertMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertMicroserviceApplication.class, args);
	}

}
