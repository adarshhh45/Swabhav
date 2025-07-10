package com.aurionpro.model;

public class PigGameTest {
	public static void main(String[] args) {
		Dice dice = new Dice();
		IpHandler inputHandler = new IpHandler();
		int totalScore = 0;
		int turnCount = 0;
		final int winScore = 20;

		while (totalScore < winScore) {
			turnCount++;
			Chance turn = new Chance(dice, inputHandler);
			int turnScore = turn.playTurn(turnCount);
			totalScore += turnScore;
			if (turnScore > 0) {
				System.out.println("Total score: " + totalScore + "\n");
			}
		}
		System.out.println("You finished in " + turnCount + " turns!\n");
		System.out.println("Game over!");
	}
}
