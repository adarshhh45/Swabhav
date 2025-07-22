package com.aurionpro.model;

public class Customer {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer(String name) {
		super();
		this.name = name;
	}
}