package com.reactorsolutions.woodland;

import org.springframework.boot.SpringApplication;

public class TestWoodlandApplication {

	public static void main(String[] args) {
		SpringApplication.from(WoodlandApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
