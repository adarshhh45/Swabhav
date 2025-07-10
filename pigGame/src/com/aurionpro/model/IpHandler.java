package com.aurionpro.model;

import java.util.Scanner;

public class IpHandler {

	Scanner sc = new Scanner(System.in);

	public String getRollOrHold() {
		while (true) {
			System.out.print("Roll or hold? (r/h): ");
			String input = sc.nextLine().trim().toLowerCase();
			if (input.equals("r") || input.equals("h")) {
				return input;
			}
			System.out.println("Invalid input. Please enter 'r' or 'h'.");
		}
	}

	public void close() {

		sc.close();

	}
}
