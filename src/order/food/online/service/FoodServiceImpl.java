package order.food.online.service;

import order.food.online.entity.Food;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import order.food.online.dao.FoodDao;
import order.food.online.dao.FoodDaoImpl;

public class FoodServiceImpl extends FoodService{

	@Override
	public void saveOrder(HSSFWorkbook  workbook,Date created) {
		HSSFSheet worksheet = workbook.getSheetAt(1);

		 int i = 1;
		 FoodDao dao = new FoodDaoImpl();
		 dao.deleteOrder();
		 while (i < worksheet.getLastRowNum()) {
			 HSSFRow row = worksheet.getRow(i++);

			 String dishName = row.getCell(0).getStringCellValue();
			   if (dishName.isEmpty()) {
				      continue;
				   }
			   		Food food=new Food();
			   		
                   food.setDishName(dishName);
                   int quantity = (int)row.getCell(1).getNumericCellValue();
                   food.setQuantity(quantity);
	               String price = row.getCell(2).getStringCellValue();
	               food.setPrice(price);
		           String weight = row.getCell(3).getStringCellValue();
		           food.setWeight(weight);
				   String restaurant = row.getCell(4).getStringCellValue();
				   food.setRestaurant(restaurant);
				   food.setCreated(created);
					
					dao.saveOrder(food);
        }
	}

	@Override
	public List<Food> getMenu() {
		FoodDao dao = new FoodDaoImpl();
		return dao.getMenu();
	}

	@Override
	public List<String> getRestaurants() {
		FoodDao dao = new FoodDaoImpl();
		return dao.getRestaurants();
	}

	@Override
	public List<String> getItems(String selItem) {
		FoodDao dao = new FoodDaoImpl();
		return dao.getItems(selItem);
	}

	@Override
	public String getBill(String resValue, String itemValue, String quantity) {
		String message = null;
		int i=Integer.parseInt(quantity); 
		FoodDao dao = new FoodDaoImpl();
		List<Food> food = dao.getBill(resValue, itemValue, quantity);
		for(Food f : food) {
			if(f.getQuantity()<i) {
				message= "fail";
			}else {
				dao.updateQuantity(resValue, itemValue, f.getQuantity()-i);
				message= "success";
			}
		}
		return message;
	}

	}
