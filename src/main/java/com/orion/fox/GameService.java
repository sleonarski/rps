package com.orion.fox;

class GameService {

    private final InputProvider inputProvider;

    public GameService(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public void play() {
        boolean play = true;

        while (play) {
            //print app interface
            printMenuScreen();
            //get user input
            InputData userInput = inputProvider.getInput();
            //check if should continue
            if (userInput.getData().equals("0")) {
                System.out.println("Goodbye!");
                play = false;
            } else if (userInput.getData().equals("1")) {
                //process input
                startGame(inputProvider);
            } else {
                System.out.println("Invalid input!\nType 1 for play or 0 for exit");
            }
        }
    }

    private void startGame(InputProvider inputProvider) {
        System.out.println("Type:\n1 - Rock\n2 - Paper\n3 - Scissors");
        InputData input = inputProvider.getInput();
        Weapon userWeapon = switch (input.getData()) {
            case "1" -> Weapon.ROCK;
            case "2" -> Weapon.PAPER;
            case "3" -> Weapon.SCISSORS;
            default -> null;
        };

        if (userWeapon != null) {
            Weapon enemyWeapon = getEnemyWeapon();
            int fightResult = fight(userWeapon, enemyWeapon);
            evaluateFight(fightResult);
        } else {
            System.out.println("Wrong weapon number!");
        }
    }

    private void evaluateFight(int figntRersult) {
        String message;
        if (figntRersult == 1) {
            message = "Player win!";
        } else if (figntRersult == -1) {
            message = "Player loose!";
        } else {
            message = "It's a draw!";
        }
        System.out.println(message);
    }

    private int fight(Weapon userWeapon, Weapon enemyWeapon) {
        return switch (userWeapon) {
            case ROCK -> enemyWeapon.equals(Weapon.ROCK) ? 0 : enemyWeapon.equals(Weapon.PAPER) ? -1 : 1;
            case PAPER -> enemyWeapon.equals(Weapon.PAPER) ? 0 : enemyWeapon.equals(Weapon.SCISSORS) ? -1 : 1;
            case SCISSORS -> enemyWeapon.equals(Weapon.SCISSORS) ? 0 : enemyWeapon.equals(Weapon.ROCK) ? -1 : 1;
        };
    }

    private Weapon getEnemyWeapon() {
        //TODO make output random
        return Weapon.ROCK;
    }

    private void printMenuScreen() {
        //TODO add logger
        System.out.println("Select action:\n1 - play game\n0 - exit");
    }
}
