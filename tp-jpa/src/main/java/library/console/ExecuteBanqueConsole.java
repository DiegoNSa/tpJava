package library.console;

import java.sql.Date;
import java.util.HashSet;

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
		myClient2.setNom("Anne");
		myClient2.setPrenom("o'nym");
		myClient2.setDateNaissance(Date.valueOf("1900-12-31"));
		myClient2.setBanque(myBanque);
		
	
		
		Adresse adresseClient2 = new Adresse();
		adresseClient2.setCodePostal(21212);
		adresseClient2.setNumero(24);
		adresseClient2.setRue("Avenue de la rue");
		adresseClient2.setVille("Totofoo");
		
		myClient2.setAdresse(adresseClient2);

		myBanque.getClients().add(myClient);
		myBanque.getClients().add(myClient2);
		
		Client myClient3 = new Client();
		myClient3.setNom("Alain");
		myClient3.setPrenom("Tairieure");
		myClient3.setDateNaissance(Date.valueOf("1910-12-31"));
		myClient3.setBanque(myOtherBanque);
		
		myOtherBanque.getClients().add(myClient2);
		
		Adresse adresseClient3 = new Adresse();
		adresseClient3.setCodePostal(54321);
		adresseClient3.setNumero(1);
		adresseClient3.setRue("Avenue de la place");
		adresseClient3.setVille("Tatafaa");
		
		myClient3.setAdresse(adresseClient3);

		
		
		
		LivretA Compte1 = new LivretA();
		Compte1.setNumero("0001-0002-0004");
		Compte1.setSolde(20000.0);
		Compte1.setTaux(2.5);
		
		LivretA Compte2 = new LivretA();
		Compte2.setNumero("0001-0002-0009");
		Compte2.setSolde(10000.0);
		Compte2.setTaux(1.5);
		Compte2.getClients().add(myClient3);

		AssuranceVie Compte3 = new AssuranceVie();
		Compte3.setNumero("0001-0002-0008");
		Compte3.setSolde(2500.0);
		Compte3.setTaux(2.5);
		Compte3.setDateFin(Date.valueOf("2000-10-10"));
		Compte3.getClients().add(myClient3);
		
		HashSet<Compte> comptes = new HashSet<Compte>();
		comptes.add(Compte1);
		myClient.setComptes(comptes);
		myClient2.setComptes(comptes);

		myClient3.getComptes().add(Compte2);
		myClient3.getComptes().add(Compte3);

		Compte1.getClients().add(myClient);
		Compte1.getClients().add(myClient2);

		
		
		em.getTransaction().begin();

		em.persist(myBanque);
		em.persist(myOtherBanque);
		em.persist(myClient);
		em.persist(myClient2);
		em.persist(myClient3);
		em.persist(Compte1);
		em.persist(Compte2);
		em.persist(Compte3);


		em.getTransaction().commit();
		
		
		TypedQuery<Compte> getClientQuery = em.createQuery("SELECT c FROM Compte c",Compte.class);
		for(Compte foundCompte : getClientQuery.getResultList()) {
			for(Client cli : foundCompte.getClients()) {
				System.out.println("Client :" + cli);
			}
		}
		
		
		System.out.println("banque id :" + myBanque.getId());
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			em.close();
		}
		System.out.println("closing");

	}

}
