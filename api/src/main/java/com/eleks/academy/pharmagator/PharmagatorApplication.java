package com.eleks.academy.pharmagator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PharmagatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(PharmagatorApplication.class, args);
	}
}
