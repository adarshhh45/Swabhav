package com.aurionpro.model;

public class PaymentProcess {
	public boolean paymentProcess(PaymentType paymentType, double amount) {
		System.out.println("processing payment of: " + amount + " via" + paymentType);
		return true;
	}
}