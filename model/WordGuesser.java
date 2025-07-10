package com.aurionpro.model;

import java.util.*;

public class WordGuesser {
	public static void main(String[] args) {

		//words
		String[] words = { "cricket", "football", "volleyball", "tennis", "dodgeball"};
		
		Random random = new Random();
		String word = words[random.nextInt(words.length)];
		int n= word.length();
		char[] blanks = new char[word.length()];
		Arrays.fill(blanks, '_');
		int lives = 6;
		Set<Character> guessed = new HashSet<>();
		Scanner sc = new Scanner(System.in);

		while (lives > 0 && new String(blanks).contains("_")) {
			System.out.println("Word: " + String.valueOf(blanks));
			System.out.println("Lives left: " + lives);
			System.out.print("Guess a letter: ");
			char guess = sc.next().toLowerCase().charAt(0);

			if (guessed.contains(guess)) {
				System.out.println("Already guessed!");
				continue;
			}
			guessed.add(guess);

			if (word.indexOf(guess) >= 0) {
				for (int i = 0; i < n; i++) {
					if (word.charAt(i) == guess) {
						blanks[i] = guess;
					}
				}
			} else {
				System.out.println("Wrong guess!");
				lives--;
			}
		}

		if (!new String(blanks).contains("_")) {
			System.out.println("Congratulations! You guessed the word: " + word);
		} else {
			System.out.println("Game Over! The word was: " + word);
		}
		sc.close();
	}
}