package com.aurionpro.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.aurionpro.model.Customer;
import com.aurionpro.model.DeliveryPartner;
import com.aurionpro.model.FoodItem;
import com.aurionpro.model.Menu;
import com.aurionpro.model.Order;
import com.aurionpro.model.PaymentProcess;
import com.aurionpro.model.PaymentType;

public class FoodOrderingTest {
	private static Menu menu = new Menu();
	private static DeliveryPartner[] partners = { new DeliveryPartner(1, "Rakesh"), new DeliveryPartner(2, "Rahul") };
	private static Scanner sc = new Scanner(System.in);
	private static boolean running = true;
	private static PaymentProcess paymentProcess = new PaymentProcess();

	public static void main(String[] args) {
		System.out.println("Welcome to Food Ordering App!");
		while (running) {
			System.out.println(" 1.Admin \n 2.Customer \n 3.Exit");
			System.out.println("Select Option:");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1: {
				System.out.println("---Admin Menu---");
				adminMenu();
				break;
			}

			case 2:
				customerMenu();
				break;
				
			case 3:
				System.out.println("Closing app");
				running =false;
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
	}

	public static void adminMenu() {
		boolean adminRunning = true;
		while (adminRunning) {
			System.out.println("\n1. Add item \n2. View menu \n3. Remove item \n4. Edit item \n5. Search item \n6. Back");
			int option = sc.nextInt();
			switch (option) {
			case 1: {
				loadMenu();
				sc.nextLine();
				System.out.println("Enter Item Name: "); 
				String name = sc.nextLine().trim();
				System.out.println("Enter Item Price: ");
				int price = sc.nextInt();
				int id = menu.getItem().size() + 1;
				menu.addItems(new FoodItem(id, name, price));
				
				saveMenu();
				System.out.println("Items added sucessfully" + " ID: " + id + "\tName: " + name + "\tPrice: Rs." + price);
				break;
			}

			case 2: {
				loadMenu();

				break;
			}
			case 3:
				 loadMenu();

	                if (menu.getItem().isEmpty()) {
	                    System.out.println("Menu is empty, nothing to remove.");
	                    break;
	                }

	                System.out.println("Enter the ID of the item to remove:");
	                int idToRemove = sc.nextInt();
	                sc.nextLine();

	                if (menu.removeItem(idToRemove)) {
	                    System.out.println("Item removed successfully.");
	                    saveMenu(); 
	                } else {
	                    System.out.println("Item not found with that ID.");
	                }
	                break;
			
			case 4: 
				loadMenu();
				if (menu.getItem().isEmpty()) {
					System.out.println("Menu is empty, nothing to edit.");
					break;
				}
				System.out.println("Enter the ID of the item to edit:");
				int idToEdit = sc.nextInt();
				sc.nextLine(); 
				System.out.println("Enter new name for the item: (press enter to keep current):");
				String newName = sc.nextLine().trim();
				System.out.println("Enter new price for the item: (or enter -1 to keep current):");
				int newPrice = sc.nextInt();

				FoodItem existingItem = menu.getItem().stream()
						.filter(item -> item.getId() == idToEdit)
						.findFirst()
						.orElse(null);

				if (existingItem != null) {
					String finalName = newName.isEmpty() ? existingItem.getName() : newName;
					int finalPrice = (newPrice == -1) ? existingItem.getPrice() : newPrice;

					if (menu.editItem(idToEdit, finalName, finalPrice)) {
						System.out.println("Item updated successfully.");
						saveMenu();
					} else {
						System.out.println("Failed to update item.");
					}
				} else {
					System.out.println("Item not found with that ID.");
				}
				break;
				  
			case 5:
				loadMenu();
				if (menu.getItem().isEmpty()) {
					System.out.println("Menu is empty, nothing to search.");
					break;
				}
				sc.nextLine(); 
				System.out.println("Enter item name to search:");
				String searchQuery = sc.nextLine();
				List<FoodItem> searchResults = menu.searchItemByName(searchQuery);

				if (searchResults.isEmpty()) {
					System.out.println("No items found matching '" + searchQuery + "'.");
				} else {
					System.out.println("\n--- Search Results for '" + searchQuery + "' ---");
					for (FoodItem item : searchResults) {
						System.out.println("ID: " + item.getId() + "\tName: " + item.getName() + "\tRs.: " + item.getPrice());
					}
					System.out.println("----------------------");
				}
				break;

			case 6:
				adminRunning = false;
				break;

			default:
				System.out.println("Invaid choice");
			}
		}
	}

	public static void loadMenu() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MENU.txt"));
			menu = (Menu) ois.readObject();
			ois.close();
			System.out.println("---Menu---");
			menu.displayMenu();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("No existing menu found.");
			menu = new Menu();
		}
	}

	public static void saveMenu() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("MENU.txt"));
			oos.writeObject(menu);
			oos.close();
			System.out.println("Menu saved successfully.");
		} catch (IOException e) {
			System.out.println("Error saving menu: " + e.getMessage());
		}

	}

	public static boolean manageCart(Order order) {
		boolean cartOpen = true;
		while (cartOpen) {
			order.recalculateTotal();
			System.out.println("\n---- Your Current Order ----");
			if (order.getItems().isEmpty()) {
				System.out.println("Your cart is empty.");
				return false; 
			}

			for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
				System.out.println(
						"ID: " + entry.getKey().getId() + " | " + entry.getKey().getName() + " x " + entry.getValue());
			}
			System.out.println("-------------------------");
			System.out.println("Total: Rs." + order.getTotal());

			System.out.println("\n1. Proceed to payment");
			System.out.println("2. Remove an item from order");
			System.out.println("3. Go back and add more items");
			int cartChoice = sc.nextInt();
			sc.nextLine();

			switch (cartChoice) {
			case 1: 
				
				order.recalculateTotal();
				double total = order.getTotal();
				double discount = (total > 500) ? total * 0.1 : 0; 
				order.setDiscount(discount);
				order.setTotal(total);

				System.out.println("Select Payment Method: \n1. Cash \n2. UPI");
				int paymentMode = sc.nextInt();
				sc.nextLine(); 
				if (paymentMode == 1) {
					order.setPaymentMode(PaymentType.CASH);
				} else {
					order.setPaymentMode(PaymentType.UPI);
				}
				Random rand = new Random();
				order.setDelivaryPartner(partners[rand.nextInt(partners.length)]);
				paymentProcess.paymentProcess(order.getPaymentMode(), order.getTotal() - order.getDiscount());
				order.invoice(order);
				return true; 
			
			case 2: 
				System.out.println("Enter the ID of the item to remove:");
				int idToRemove = sc.nextInt();
				sc.nextLine(); 
				FoodItem itemToRemove = order.getItems().keySet().stream().filter(key -> key.getId() == idToRemove)
						.findFirst().orElse(null);
				if (itemToRemove != null) {
					order.removeItemFromOrder(itemToRemove);
				} else {
					System.out.println("Invalid ID. Item not in your cart.");
				}
				break;

			case 3: 
				return false; 
				

			default:
				System.out.println("Invalid Choice. Please try again.");
			}
		}
		return false;
	}

	public static void customerMenu() {
		boolean customerRunning = true;
		System.out.println("Enter you name: ");
		String name = sc.next();
		System.out.println("Welcome " + name);
		Customer customer = new Customer(name);
		Order order = new Order();
		
		while (customerRunning) {
			System.out.println("\n1. View Menu and Add Item \n2. View Cart and Finalize Order \n3. Search item \n4. Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				loadMenu();
				System.out.println("Enter ID to add (or 0 to go back):");
				int id = sc.nextInt();
				if (id == 0)
					continue;
				FoodItem item = menu.getItem().stream().filter(f -> f.getId() == id).findFirst().orElse(null);

				if (item != null) {
					System.out.println("Enter the Quantity:");
					int qty = sc.nextInt();
					sc.nextLine();
					order.addItems(item, qty);
					System.out.println(qty+"x  "+ item.getName() + " added to your order.");
				} else {
					System.out.println("Invalid item ID.");
				}
				break;

			}
			case 2:
				if (order.getItems().isEmpty()) {
                    System.out.println("Your cart is empty.\nPlease add items first.");
                    continue; 
                }
			    boolean orderIsFinalized = manageCart(order);
			    if (orderIsFinalized) {
                    customerRunning = false;
                }
                break;
                
			case 3: 
            	loadMenu(); 
            	if (menu.getItem().isEmpty()) {
                    System.out.println("Menu is empty, nothing to search.");
                    break;
                }
                sc.nextLine();
                System.out.println("Enter item name to search:");
                String searchQuery = sc.nextLine();
                List<FoodItem> searchResults = menu.searchItemByName(searchQuery);

                if (searchResults.isEmpty()) {
                    System.out.println("No items found matching '" + searchQuery + "'.");
                } else {
                    System.out.println("\n--- Search Results for '" + searchQuery + "' ---");
                    for (FoodItem item : searchResults) {
                        System.out.println("ID: " + item.getId() + "\tName: " + item.getName() + "\tRs.: " + item.getPrice());
                    }
                    System.out.println("-------------------------");
                }
                break;
                case 4:
                	  customerRunning = false;
                      break;
                  
                  default:
                      System.out.println("Invalid choice");
			}
		}
	}
}