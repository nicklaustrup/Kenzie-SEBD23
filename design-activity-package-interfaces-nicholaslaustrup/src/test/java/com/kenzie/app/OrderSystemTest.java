package com.kenzie.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderSystemTest {
	public static OnlineCustomer getOnlineCustomer() {
		OnlineCustomer customer = new OnlineCustomer();
		return customer;
	}

	public static CardPayment getCardPayment() {
		CardPayment payment = new CardPayment();
		return payment;
	}

	public static OnlineOrderSystem getOnlineOrderSystem() {
		OnlineOrderSystem system = new OnlineOrderSystem();
		return system;
	}

	public static FoodItem getFoodItem() {
		FoodItem food = new FoodItem();
		return food;
	}

	public static FoodOrder getFoodOrder() {
		FoodOrder order = new FoodOrder();
		return order;
	}

	public static RestaurantMenu getMenu() {
		RestaurantMenu menu = new RestaurantMenu();
		return menu;
	}

	@Test
	void canCreateCardPayment() {
        CardPayment payment = getCardPayment();
		assertTrue(payment instanceof Payment, "CardPayment is instance of Payment");
	}

	@Test
	void canCreateCustomer() {
		OnlineCustomer customer = getOnlineCustomer();
		assertTrue(customer instanceof Customer, "OnlineCustomer is instance of Customer");
	}

	@Test
	void canCreateOrderSystem() {
		OnlineOrderSystem system = getOnlineOrderSystem();
		assertTrue(system instanceof OrderSystem, "OnlineOrderSystemr is instance of OrderSystem");
	}

	@Test
	void canCreateMenu() {
		RestaurantMenu menu = getMenu();
		assertTrue(menu instanceof Menu, "RestaurantMenu is instance of Menu");
	}

	@Test
	void canCreateMenuItem() {
		FoodItem item = getFoodItem();
		assertTrue(item instanceof MenuItem, "FoodItem is instance of MenuItem");
	}

	@Test
	void canCreateOrder() {
		FoodOrder order = getFoodOrder();
		assertTrue(order instanceof Order, "FoodOrder is instance of Order");
	}
}
