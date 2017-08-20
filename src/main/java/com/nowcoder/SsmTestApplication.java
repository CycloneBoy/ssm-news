package com.nowcoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.nowcoder.*")
@SpringBootApplication
public class SsmTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsmTestApplication.class, args);
	}
}
