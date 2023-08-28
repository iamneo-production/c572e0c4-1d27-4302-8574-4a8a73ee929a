package com.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DataVisualizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataVisualizationApplication.class, args);
	}

}
