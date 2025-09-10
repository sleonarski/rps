package com.orion.fox;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class RpsApplication {

	public static void main(String[] args) {
//		SpringApplication.run(RpsApplication.class, args);
		//TODO temporary solution for command line app only
		new SpringApplicationBuilder(RpsApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}

	@Bean
	GameManager gameManager(GameService gameService) {
		return new GameManager(gameService);
	}

	@Bean
	GameService gameService(InputProvider inputProvider) {
        return new GameService(inputProvider);
	}

	@Bean
	BufferedReader bufferedReader() {
		return new BufferedReader(new InputStreamReader(System.in));
	}
}
