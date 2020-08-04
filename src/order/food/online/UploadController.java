package order.food.online;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import order.food.online.entity.Food;
import order.food.online.service.FoodService;
import order.food.online.service.FoodServiceImpl;

@RestController
public class UploadController {
	
	@PostMapping("/fileUpload/{created}")
	public @ResponseBody List<Food> postEmployeeData(@RequestParam("file") MultipartFile multipartFile,
			@PathVariable("created") Date created) throws IOException {
		HSSFWorkbook  workbook = new HSSFWorkbook(multipartFile.getInputStream());
		
		FoodService service = new FoodServiceImpl();
		
		service.saveOrder(workbook,created);
		List<Food> menu = service.getMenu();
	
		return menu;
	
	}
	
	@GetMapping("/getRestaurants")
	public @ResponseBody TreeSet<String> getRestaurants(){
		FoodService service = new FoodServiceImpl();
		List<String> res = service.getRestaurants();
		TreeSet<String> treeSet = new TreeSet<String>(res);
		return treeSet;
		
	}
	
	@GetMapping("/getItems/{selItem}")
	public @ResponseBody List<String> getItems(@PathVariable("selItem") String selItem){
		FoodService service = new FoodServiceImpl();
		List<String> res = service.getItems(selItem);
		return res;
		
	}
	
	@GetMapping("/getBill/{resValue}/{itemValue}/{quantity}")
	public @ResponseBody String getBill(@PathVariable("resValue") String resValue, @PathVariable("itemValue") String itemValue, 
			@PathVariable("quantity") String quantity){
		FoodService service = new FoodServiceImpl();
		String res = service.getBill(resValue, itemValue, quantity);
		return res;
		
	}
}