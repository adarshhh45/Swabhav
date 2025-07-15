package com.aurionpro.model;

public class BankAccount {
	private double balance;

	public BankAccount(double initialBalance) {
		this.balance = initialBalance;
	}

	public void deposit(double amount) throws NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException("Cannot deposit negative amount.");
		}
		balance += amount;
	}

	public void withdraw(double amount) throws NegativeAmountException, InsufficientFundsException {
		if (amount < 0) {
			throw new NegativeAmountException("Cannot withdraw negative amount.");
		}
		if (amount > balance) {
			throw new InsufficientFundsException("Insufficient funds for withdrawal.");
		}
		balance -= amount;
	}

	public double getBalance() {
		return balance;
	}
}
