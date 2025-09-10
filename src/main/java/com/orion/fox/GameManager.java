package com.orion.fox;

import org.springframework.boot.CommandLineRunner;

public class GameManager implements CommandLineRunner {

    private final GameService gameService;

    public GameManager(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) {
        gameService.play();
    }
}
