package com.insurer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.insurer")
public class InsurerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurerApplication.class, args);
	}

}
