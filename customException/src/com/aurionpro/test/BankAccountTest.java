package com.aurionpro.test;

import com.aurionpro.model.BankAccount;
import com.aurionpro.model.InsufficientFundsException;
import com.aurionpro.model.NegativeAmountException;

public class BankAccountTest {
	public static void main(String[] args) {
		BankAccount account = new BankAccount(15000);
		System.out.println("Balance: " + account.getBalance());
		try {
			account.deposit(3500);
			System.out.println("After deposit balance: " + account.getBalance());
			account.withdraw(20000);
			System.out.println("After withdrawal balance: " + account.getBalance());
			account.withdraw(2000);
		} catch (NegativeAmountException | InsufficientFundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		try {
			account.deposit(-1000);
		} catch (NegativeAmountException e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}
}
