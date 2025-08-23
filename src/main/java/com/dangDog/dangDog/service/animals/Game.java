package com.dangDog.dangDog.service.animals;

import java.util.Random;

public class Game {
    private Integer player1;
    private Integer player2;
    private Random random = new Random();

    public Game() {
        playAgain();
    }

    public boolean isWon() {
        if (player1 * 2 < player2) {
            return true;
        } else if (player2 * 2 < player1) {
            return true;
        }
        return false;
    }

    public String printWinner() {
        if (isWon()) {
            if (player1 * 2 < player2) {
                return "winner player 2";
            } else if (player2 * 2 < player1) {
                return "winner player 1";
            }
        }
        throw new IllegalStateException("HANI BIRI KAZANMISTI ?");
    }

    public void playAgain() {
        player1 = random.nextInt(6);
        player2 = random.nextInt(6);
        System.out.println("p1: " + player1 + " p2: " + player2);
    }
}
