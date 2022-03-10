package dataAccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Users.User;


public class RegisterDb {
	
	private EntityManager dbConnector;
	private EntityManagerFactory emf;
	final String fileName = "db/Register.odb";
	
	public RegisterDb() {
	emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
	dbConnector = emf.createEntityManager();
	System.out.println("DataBase opened");
	}
	
	
	// later we will add further operations here
	public void closeDb() {
	dbConnector.close();
	System.out.println("DataBase closed");
	}
	
	public void storeUser(String fname,String lname,String email,String password) {
		dbConnector.getTransaction().begin();
		User user = new User(fname,lname,email,password);
		dbConnector.persist(user);
		dbConnector.getTransaction().commit();
		System.out.println(user + " has been saved");
		}
	

}
