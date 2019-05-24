package fr.pizzeria.mem;

import java.util.ArrayList;

import javax.persistence.*;

import fr.pizzeria.model.Pizza;

public class PizzaJpaDao implements IPizzaDao {

	@Override
	public ArrayList<Pizza> findAllPizzas() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria-jpa");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		TypedQuery<Pizza> resultQuery = em.createQuery("SELECT p FROM Pizza p",Pizza.class);
		ArrayList<Pizza> pizzaList = (ArrayList<Pizza>)resultQuery.getResultList();
		em.close();
		return pizzaList;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria-jpa");
		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();
		em.persist(pizza);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		Pizza pizzaFound = findPizzaByCode(codePizza);
		
		if(pizzaFound != null) {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria-jpa");
			EntityManager em = entityManagerFactory.createEntityManager();

			TypedQuery<Pizza> resultQuery = em.createQuery("Select p from Pizza p WHERE code=:pcode",Pizza.class);
			resultQuery.setParameter("pcode", codePizza);

			try {
				Pizza pizzaToUpdate = resultQuery.getSingleResult();

				em.getTransaction().begin();
				pizzaToUpdate.setCategory(pizza.getCategory());
				pizzaToUpdate.setCode(pizza.getCode());
				pizzaToUpdate.setLibelle(pizza.getLibelle());
				pizzaToUpdate.setPrix(pizza.getPrix());
				em.merge(pizzaToUpdate);
				em.getTransaction().commit();
			}catch(NoResultException ex) {
			}finally {
				em.close();
			}		
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		// TODO Auto-generated method stub
		Pizza pizza = findPizzaByCode(codePizza);
		if(pizza != null) {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria-jpa");
			EntityManager em = entityManagerFactory.createEntityManager();

			TypedQuery<Pizza> resultQuery = em.createQuery("Select p from Pizza p WHERE code=:pcode",Pizza.class);
			resultQuery.setParameter("pcode", codePizza);

			try {
				Pizza pizzaToDelete = resultQuery.getSingleResult();
				if(pizzaToDelete!= null) {
					em.getTransaction().begin();
					em.remove(pizzaToDelete);
					em.getTransaction().commit();
				}
			}catch(NoResultException ex) {
			}finally {
				em.close();
			}
		}
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria-jpa");
		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Pizza> resultQuery = em.createQuery("Select p from Pizza p WHERE code=:pcode",Pizza.class);
		resultQuery.setParameter("pcode", codePizza);
	
		Pizza pizza = resultQuery.getSingleResult();
		em.close();
		return pizza;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria-jpa");
		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Pizza> resultQuery = em.createQuery("Select p from Pizza p WHERE code=:pcode",Pizza.class);
		resultQuery.setParameter("pcode", codePizza);
		boolean isFound = false;

		try {
			Pizza pizza = resultQuery.getSingleResult();
			if(pizza!= null) {
				isFound = true;
			}
		}catch(NoResultException ex) {
			isFound = false;
		}finally {
			em.close();
		}
		
		
		return isFound;
	}

}
