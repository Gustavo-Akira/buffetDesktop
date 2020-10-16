package gustavoakira.javafx.buffet.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory factory;
	
	private static Configuration configuration;
	
	private static SessionFactory buildSessionFactory() {
		try {
			configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			factory = configuration.buildSessionFactory();
			return factory;
		}catch(Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		if(factory == null)
			factory = buildSessionFactory();
		
		return factory;
	}
	
}
