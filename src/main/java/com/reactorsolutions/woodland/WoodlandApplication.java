package com.reactorsolutions.woodland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class WoodlandApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoodlandApplication.class, args);
	}

}
