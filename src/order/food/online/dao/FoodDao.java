package order.food.online.dao;

import java.util.List;

import order.food.online.entity.Food;

public abstract class FoodDao {
	public abstract void saveOrder(Food food);

	public abstract List<Food> getMenu();

	public abstract List<String> getRestaurants();

	public abstract List<String> getItems(String selItem);

	public abstract List<Food> getBill(String resValue, String itemValue, String quantity);

	public abstract void updateQuantity(String resValue, String itemValue, int quantity);

	public abstract void deleteOrder();
}
