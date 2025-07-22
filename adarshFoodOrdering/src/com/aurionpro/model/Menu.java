package com.aurionpro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Menu implements Serializable {
	
	private List<FoodItem> items = new ArrayList<>();

	public void addItems(FoodItem item) {
		items.add(item);
	}

	public List<FoodItem> getItem() {
		return items;
	}

	public void displayMenu() {
		for (FoodItem item : items) {
			System.out.println("ID: " + item.getId() + "\tName: " + item.getName() + "\tRs.: " + item.getPrice());
		}
	}

	public boolean removeItem(int id) {
		Optional<FoodItem> itemToRemove = items.stream().filter(item -> item.getId() == id).findFirst();

		if (itemToRemove.isPresent()) {
			items.remove(itemToRemove.get());
			return true;
		}
		return false;
	}
	
	public boolean editItem(int id, String newName, int newPrice) {
		Optional<FoodItem> itemToEdit = items.stream().filter(item -> item.getId() == id).findFirst();
		if (itemToEdit.isPresent()) {
			FoodItem item = itemToEdit.get();
			item.setName(newName);
			item.setPrice(newPrice);
			return true;
		}
		return false;
	}
	
	public List<FoodItem> searchItemByName(String query) {
		if (query == null || query.trim().isEmpty()) {
			return new ArrayList<>(); // Return empty list for empty query
		}
		String lowerCaseQuery = query.toLowerCase().trim();
		return items.stream()
				.filter(item -> item.getName().toLowerCase().contains(lowerCaseQuery))
				.collect(Collectors.toList());
	}
}