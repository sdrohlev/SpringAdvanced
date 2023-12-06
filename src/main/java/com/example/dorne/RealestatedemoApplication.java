package com.example.dorne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RealestatedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealestatedemoApplication.class, args);
	}

}
