package com.fbs.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FbsFlightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FbsFlightServiceApplication.class, args);
	}

}
