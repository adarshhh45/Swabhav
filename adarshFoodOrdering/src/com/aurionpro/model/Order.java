package com.aurionpro.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
	private Map<FoodItem, Integer> items = new HashMap<>();
	private int quantity;
	private double total;
	private double discount;
	private PaymentType paymentMode;
	private DeliveryPartner deliveryPartner;

	public Map<FoodItem, Integer> getItems() {
		return items;
	}

	public void setItems(Map<FoodItem, Integer> items) {
		this.items = items;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount2) {
		this.discount = discount2;
	}

	public PaymentType getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentType upi) {
		this.paymentMode = upi;
	}

	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}

	public void setDelivaryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

	public void addItems(FoodItem item, int quantity) {
		items.put(item, items.getOrDefault(item, 0) + quantity);
	}

	public void removeItemFromOrder(FoodItem item) {
		if (items.containsKey(item)) {
			items.remove(item);
			System.out.println(item.getName() + " removed from your order.");
		} else {
			System.out.println("Item not found in your order.");
		}
	}

	public void recalculateTotal() {
		double currentTotal = 0;
		for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
			currentTotal += entry.getKey().getPrice() * entry.getValue();
		}
		this.total = currentTotal;
	}

	public void invoice(Order order) {
		System.out.println("\n--- Invoice ---");
		recalculateTotal();

		for (Map.Entry<FoodItem, Integer> entry : getItems().entrySet()) {
			FoodItem item = entry.getKey();
			int qty = entry.getValue();
			System.out.println("Item: " + item.getName() + " x " + qty + " - Rs." + (item.getPrice() * qty));
		}
		System.out.println("---");
		double discount = (getTotal() > 500) ? getTotal() * 0.10 : 0;
		setDiscount(discount);

		System.out.println("Amount: Rs." + getTotal());
		System.out.println("Discount: Rs." + getDiscount());
		System.out.println("Final amount: Rs." + (getTotal() - getDiscount()));
		System.out.println("Payment Mode: " + getPaymentMode());
		System.out.println("Delivery Partner: " + getDeliveryPartner().getName());
		System.out.println("------------");
	}

}