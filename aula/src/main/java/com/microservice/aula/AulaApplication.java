package com.microservice.aula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AulaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AulaApplication.class, args);
	}

}
