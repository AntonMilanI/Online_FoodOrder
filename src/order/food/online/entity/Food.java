package order.food.online.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Food")
public class Food {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "dish_name")
	private String dishName;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "weight")
	private String weight;
	
	@Column(name = "restaurant")
	private String restaurant;
	
	@Column(name = "created_ts")
	private Date created;

	public Food() {
	
	}

	public Food(String dishName, int quantity, String price, String weight, String restaurant, Date created) {
		this.dishName = dishName;
		this.quantity = quantity;
		this.price = price;
		this.weight = weight;
		this.restaurant = restaurant;
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", dishName=" + dishName + ", quantity=" + quantity + ", price=" + price + ", weight="
				+ weight + ", restaurant=" + restaurant + ", created=" + created + "]";
	}
	
}
