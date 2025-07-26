package com.example.UberSocketServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.example.uberentityservice.models")
public class UberSocketServerApplication {

	static {
		Dotenv dotenv = Dotenv.configure()
				.directory(".")
				.filename(".env")
				.load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(UberSocketServerApplication.class, args);
	}
}