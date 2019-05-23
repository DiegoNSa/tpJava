package fr.pizzeria.mem;

import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.bdd.*;

import java.util.ArrayList;

public class PizzaJdbcDao implements IPizzaDao {
	
	private ArrayList<Pizza> pizzaArray;
	
	
	
	public PizzaJdbcDao() {
		pizzaArray = new ArrayList<Pizza>();
	}
	
	
	//increase the size of an array	
	public ArrayList<Pizza> findAllPizzas() {
		return BddManager.retrievePizzas();
	}
	
	public void deletePizza(String codePizza) {
		if(pizzaExists(codePizza)) {
			BddManager.removePizza(codePizza);
			return;
		}
		System.out.println("Attention : aucune pizza avec le code donn�. Rien n'a �t� supprim�.");
	}

	
	public boolean pizzaExists(String codePizza) {
		return BddManager.checkPizza(codePizza);

	}
	
	public void saveNewPizza(Pizza pizza) {
		if(!pizzaExists(pizza.getCode())) {
			BddManager.addPizza(pizza);
		}
	}
	
	public void updatePizza(String codePizza, Pizza pizza) {
		if(pizzaExists(codePizza)) {
			BddManager.updatePizza(codePizza,pizza);
		}
	}
	
	public Pizza findPizzaByCode(String codePizza) {
		if(pizzaExists(codePizza)) {
			return BddManager.findPizza(codePizza);
		}
		System.out.println("Erreur : aucune pizza avec le code donné.");
		return null;
	}
}
