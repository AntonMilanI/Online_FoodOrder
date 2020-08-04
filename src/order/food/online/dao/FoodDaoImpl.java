package order.food.online.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;


import order.food.online.entity.Food;

public class FoodDaoImpl extends FoodDao{

	static String tns = "oracle.net.tns_admin"; 
	static String property = "E:\\app\\AlbertAlan\\product\\12.2.0\\dbhome_1\\network\\admin";
	@Override
	public void saveOrder(Food food) {
		System.setProperty(tns, property);
		 try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			session.beginTransaction();
			session.save(food);
			session.getTransaction().commit();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOrder() {
		System.setProperty(tns, property);
		 try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			session.beginTransaction();
			session.createQuery("delete from Food").executeUpdate();
			session.getTransaction().commit();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Food> getMenu() {
		List<Food> menu = new ArrayList<Food>();
		System.setProperty(tns, property);
		
		 try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			session.beginTransaction();
			menu = session.createQuery("from Food",Food.class).getResultList();
			System.out.println(menu);
			session.getTransaction().commit();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRestaurants() {
		List<String> res = new ArrayList<String>();
		System.setProperty(tns, property);
		
		 try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			session.beginTransaction();
			res = session.createQuery("select f.restaurant from Food f").getResultList();
			System.out.println(res);
			session.getTransaction().commit();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getItems(String selItem) {
		List<String> res = new ArrayList<String>();
		System.setProperty(tns, property);
		
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			session.beginTransaction();
			res = session.createQuery("select f.dishName from Food f where f.restaurant=" + "'" +selItem +"'" ).getResultList();
			System.out.println(res);
			session.getTransaction().commit();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Food> getBill(String resValue, String itemValue, String quantity) {
		System.setProperty(tns, property);
		List<Food> food = new ArrayList<Food>();
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			session.beginTransaction();
			food = session.createQuery("from Food f where f.restaurant=" + "'" +resValue +"' and f.dishName=" + "'" + itemValue+ "'",Food.class).getResultList();
			
			session.getTransaction().commit();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return food;
	}

	@Override
	public void updateQuantity(String resValue, String itemValue, int quantity) {
		List<Food> food = new ArrayList<Food>();
		System.setProperty(tns, property);
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			
			session.beginTransaction();
			food = session.createQuery("from Food f where f.restaurant=" + "'" +resValue +"' and f.dishName=" + "'" + itemValue+ "'",Food.class).getResultList();
			for(Food f : food) {
				f.setQuantity(quantity);
			}
			session.getTransaction().commit();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	}
