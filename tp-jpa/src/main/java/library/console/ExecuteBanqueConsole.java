package library.console;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import banque.entity.*;

public class ExecuteBanqueConsole {
	public static void execute() {
		System.out.println("Hello world");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banque-tp-jpa");
		EntityManager em = entityManagerFactory.createEntityManager();

		try {
		Banque myBanque = new Banque();
		myBanque.setNom("LCB");
		
		Banque myOtherBanque = new Banque();
		myOtherBanque.setNom("LCD");
		
		Client myClient = new Client();
		myClient.setNom("Bon");
		myClient.setPrenom("Jean");
		myClient.setDateNaissance(Date.valueOf("2017-01-01"));
		myClient.setBanque(myBanque);
		
		Adresse adresseClient = new Adresse();
		adresseClient.setCodePostal(01234);
		adresseClient.setNumero(42);
		adresseClient.setRue("rue de l'avenue");
		adresseClient.setVille("Villa");

		myClient.setAdresse(adresseClient);
		
		Client myClient2 = new Client();
		myClient.setNom("Anne");
		myClient.setPrenom("o'nym");
		myClient.setDateNaissance(Date.valueOf("1900-12-31"));
		myClient.setBanque(myOtherBanque);
		
		Adresse adresseClient2 = new Adresse();
		adresseClient.setCodePostal(21212);
		adresseClient.setNumero(24);
		adresseClient.setRue("Avenue de la rue");
		adresseClient.setVille("Totofoo");
		
		myClient2.setAdresse(adresseClient2);

		
		em.getTransaction().begin();

		em.persist(myBanque);
		em.persist(myOtherBanque);
		em.persist(myClient);
		em.persist(myClient2);

		em.getTransaction().commit();
		
		System.out.println("banque id :" + myBanque.getId());
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			em.close();
		}
		System.out.println("closing");

	}

}
