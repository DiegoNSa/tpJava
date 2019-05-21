package library.console;

import javax.persistence.*;

import library.entity.*;

public class App {

	public static void main(String[] args) {
		System.out.println("Hello world");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tp-jpa");
		EntityManager em = entityManagerFactory.createEntityManager();

		Livre newLivre = em.find(Livre.class, 2);
		if(newLivre != null) {
			System.out.println(newLivre.getId() + " " + newLivre.getTitre() + " " + newLivre.getAuteur());
		}
		
		TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l WHERE titre=:titre",Livre.class);
		query.setParameter("titre", "Guerre et paix");
		
		try {
		Livre foundLivre = query.getSingleResult();
		System.out.println(foundLivre.getId() + " " + foundLivre.getTitre() + " " + foundLivre.getAuteur());
		}catch(Exception ex) {
			System.out.println("Livre not found");
		}
		
		
		TypedQuery<Emprunt> getEmpruntQuery = em.createQuery("SELECT e FROM Emprunt e WHERE ID=:idEmp",Emprunt.class);
		getEmpruntQuery.setParameter("idEmp", "2");

		try {
			Emprunt foundEmprunt = getEmpruntQuery.getSingleResult();
			System.out.println("Resultat pour Emprunt 2");

			for(Livre livre : foundEmprunt.getLivres()) {
				System.out.println(livre.getId() + " " + livre.getTitre() + " " + livre.getAuteur());

			}
		}catch(Exception ex) {
			System.out.println("Livre not found");
		}
			
		
		TypedQuery<Client> getClientQuery = em.createQuery("SELECT c FROM Client c WHERE ID=:idClt",Client.class);
		getClientQuery.setParameter("idClt", "2");

		try {
			Client foundClient = getClientQuery.getSingleResult();
			System.out.println("Resultat pour Client 2");

			for(Emprunt emprunt : foundClient.getEmprunts()) {
				System.out.println(emprunt.getId() + " " + emprunt.getDateDebut() + " " + emprunt.getDateFin());
				for(Livre livre : emprunt.getLivres()) {
					System.out.println("livre : " + livre.getId() + " " + livre.getTitre() + " " + livre.getAuteur());

				}
			}
		}catch(Exception ex) {
			System.out.println("Livre not found");
		}

	}

}
