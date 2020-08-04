package order.food.online.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import order.food.online.entity.Food;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
            	sessionFactory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Food.class)
						.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}