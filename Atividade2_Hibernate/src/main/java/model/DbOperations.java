package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DbOperations {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	public final static Logger logger = Logger.getLogger(DbOperations.class);

	// This Method Is Used To Create The Hibernate's SessionFactory Object
	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

	// Method 1: This Method Used To Create A New Student Record In The Database Table
	public static int createRecord(String nome, String endereco, String telefone)  {
        int id = 0;
		Contato contatoObj = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entities
			contatoObj = new Contato();
			contatoObj.setNome(nome);
			contatoObj.setEndereco(endereco);
			contatoObj.setTelefone(telefone);
			sessionObj.save(contatoObj);
            id = contatoObj.getId();

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nSuccessfully Created record in The Database!\n");
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}

        return id;
	}

	// Method 2: This Method Is Used To Display The Records From The Database Table
	@SuppressWarnings("unchecked")
	public static List<Contato> displayRecords() {
		List<Contato> contatosList = new ArrayList<Contato>();
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			contatosList = sessionObj.createQuery("FROM Contato").list();
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
		return contatosList;
	}

	// Method 3: This Method Is Used To Update A Record In The Database Table
	public static void updateRecord(int id) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			// Creating Transaction Entity
			Contato contatObj = (Contato) sessionObj.get(Contato.class, id);
			contatObj.setNome("Jose");
			contatObj.setEndereco("AV AAA, 777");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nContato With Id?= " + id + " Is Successfully Updated In The Database!\n");
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
	public static void deleteRecord(Integer id) {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Contato contatoObj = findRecordById(id);
			sessionObj.delete(contatoObj);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nContato With Id?= " + id + " Is Successfully Deleted From The Database!\n");
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// Method 4(b): This Method To Find Particular Record In The Database Table
	public static Contato findRecordById(Integer id) {
		Contato findContatoObj = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			findContatoObj = (Contato) sessionObj.load(Contato.class, id);
        } catch(HibernateException ex) {
            ex.printStackTrace();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
		return findContatoObj;
	}

	// Method 5: This Method Is Used To Delete All Records From The Database Table
	public static void deleteAllRecords() {
		try {
			// Getting Session Object From SessionFactory
			sessionObj = buildSessionFactory().openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			Query queryObj = sessionObj.createQuery("DELETE FROM Contato");
			queryObj.executeUpdate();

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
			logger.info("\nSuccessfully Deleted All Records From The Database Table!\n");
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				logger.info("\n.......Transaction Is Being Rolled Back.......\n");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
