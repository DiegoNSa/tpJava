package fr.pizzeria.mem;

import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;

import java.util.ArrayList;

public class PizzaDao implements IPizzaDao {
	
	private ArrayList<Pizza> pizzaArray;
	
	public ArrayList<Pizza> getPizzaArray() {
		return pizzaArray;
	}

	public void setPizzaArray(ArrayList<Pizza> pizzaArray) {
		this.pizzaArray = pizzaArray;
	}

	public PizzaDao() {
		pizzaArray = new ArrayList<Pizza>();
		try {
			pizzaArray.add(new Pizza("PEP","Pépéroni",12.5,CategoryPizza.VIANDE));
			pizzaArray.add(new Pizza("MAR","Margherita",14.0,CategoryPizza.SANS_VIANDE));
			pizzaArray.add(new Pizza("REIN","La Reine",11.5,CategoryPizza.VIANDE));
			pizzaArray.add(new Pizza("FRO","La 3 fromage",12.0,CategoryPizza.SANS_VIANDE));
			pizzaArray.add(new Pizza("CAN","La cannibale",12.5,CategoryPizza.VIANDE));
			pizzaArray.add(new Pizza("SAV","La savoyarde",13.0,CategoryPizza.VIANDE));
			pizzaArray.add(new Pizza("ORI","L'orientale",13.5,CategoryPizza.VIANDE));
			pizzaArray.add(new Pizza("IND","L'indienne",14.0,CategoryPizza.VIANDE));
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	//increase the size of an array	
	public ArrayList<Pizza> findAllPizzas() {
		return pizzaArray;
	}
	
	public void deletePizza(String codePizza) {
		
		for(Pizza currentPizza : pizzaArray) {
			if(currentPizza.getCode().equals(codePizza)) {
				pizzaArray.remove(currentPizza);
				return;
			}
		}
		System.out.println("Attention : aucune pizza avec le code donn�. Rien n'a �t� supprim�.");
	}

	
	public boolean pizzaExists(String codePizza) {
		for(Pizza currentPizza : pizzaArray) {
			if(currentPizza.getCode().equals(codePizza)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void saveNewPizza(Pizza pizza) {
		if(!this.pizzaExists(pizza.getCode())) {
			pizzaArray.add(pizza);
		}
	}
	
	public void updatePizza(String codePizza, Pizza pizza) {
		Pizza pizzaToModify = this.findPizzaByCode(codePizza);
		
		if(pizzaToModify != null && (pizza.getCode().equals(codePizza) || !this.pizzaExists(pizza.getCode()))) {
			int indexOfPizza = pizzaArray.indexOf(pizzaToModify);
			pizzaArray.set(indexOfPizza, pizza);
		}
	}
	
	public Pizza findPizzaByCode(String codePizza) {
		for(Pizza currentPizza : pizzaArray) {
			if(currentPizza.getCode().equals(codePizza)) {
				return currentPizza;
			}
		}
		System.out.println("Erreur : aucune pizza avec le code donné.");
		return null;
	}
}
