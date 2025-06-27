package com.example.supperapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SupperappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupperappApplication.class, args);
	}

}
