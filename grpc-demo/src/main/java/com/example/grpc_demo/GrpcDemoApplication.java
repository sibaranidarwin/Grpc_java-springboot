package com.example.grpc_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcDemoApplication {

	public static void main(String[] args) {
		System.out.println("Starting application...");
		SpringApplication.run(GrpcDemoApplication.class, args);
		System.out.println("Application started.");
	}


}
