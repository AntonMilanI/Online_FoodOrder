package order.food.online.service;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import order.food.online.entity.Food;

public abstract class FoodService {
	public abstract void saveOrder(HSSFWorkbook  workbook,Date created);

	public abstract List<Food> getMenu();

	public abstract List<String> getRestaurants();

	public abstract List<String> getItems(String selItem);

	public abstract String getBill(String resValue, String itemValue, String quantity);
}
