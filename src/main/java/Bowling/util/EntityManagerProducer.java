package Bowling.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {
	
	private static EntityManagerFactory emf  = Persistence.createEntityManagerFactory("JPAStrutsPU");
	private static EntityManager entityManager = emf.createEntityManager();
	
	private EntityManagerProducer(){
		
	}
	
	public static EntityManager getEntityManager(){	
		return entityManager;	
	}
}
