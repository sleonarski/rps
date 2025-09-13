package com.orion.fox;

import java.util.Random;

class GameService {

    private final InputProvider inputProvider;

    public GameService(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public void play() {
        boolean play;

        //print app interface
        printMenuScreen();
        InputData userInput = inputProvider.getInput();
        if (userInput.getData().equals("0")) {
            play = false;
            System.out.println("Goodbye!");
        } else {
            play = true;
        }

        while (play) {
            //get user input
            System.out.println("Type:\n1 - Rock\n2 - Paper\n3 - Scissors\n0 - Back to menu");
            userInput = inputProvider.getInput();
            //check if should continue
            if (userInput.getData().equals("0")) {
                play = false;
                play();
            } else {
                //process input
                Weapon userWeapon = switch (userInput.getData()) {
                    case "1" -> Weapon.ROCK;
                    case "2" -> Weapon.PAPER;
                    case "3" -> Weapon.SCISSORS;
                    default -> null;
                };

                if (userWeapon != null) {
                    printUserWeapon(userWeapon);
                    Weapon enemyWeapon = getEnemyWeapon();
                    printEnemyWeapon(enemyWeapon);
                    int fightResult = fight(userWeapon, enemyWeapon);
                    evaluateFight(fightResult);
                } else {
                    System.out.println("Wrong weapon number!");
                }
            }
        }
    }

    private void printEnemyWeapon(Weapon enemyWeapon) {
        printWeapon("Enemy", enemyWeapon);
    }

    private void printUserWeapon(Weapon userWeapon) {
        printWeapon("User", userWeapon);
    }

    private void printWeapon(String player, Weapon weapon) {
        System.out.printf("%s used %s\n", player, weapon);
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
        Random random = new Random();
        int enemyInput = random.nextInt(3);
        return switch (enemyInput) {
            case 0 -> Weapon.ROCK;
            case 1 -> Weapon.PAPER;
            case 2 -> Weapon.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + enemyInput);
        };
    }

    private void printMenuScreen() {
        System.out.println("Select action:\n1 - play game\n0 - exit");
    }
}
