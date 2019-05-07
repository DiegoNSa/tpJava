package fr.pizzeria.mem;

import fr.pizzeria.model.Pizza;
import java.util.ArrayList;

public class PizzaDao implements IPizzaDao {
	private ArrayList<Pizza> pizzaArray;
	
	public PizzaDao() {
		pizzaArray = new ArrayList<Pizza>();
		pizzaArray.add(new Pizza("PEP","Pépéroni",12.5));
		pizzaArray.add(new Pizza("MAR","Margherita",14.0));
		pizzaArray.add(new Pizza("REIN","La Reine",11.5));
		pizzaArray.add(new Pizza("FRO","La 3 fromage",12.0));
		pizzaArray.add(new Pizza("CAN","La cannibale",12.5));
		pizzaArray.add(new Pizza("SAV","La savoyarde",13.0));
		pizzaArray.add(new Pizza("ORI","L'orientale",13.5));
		pizzaArray.add(new Pizza("IND","L'indienne",14.0));
	}
	//increase the size of an array	
	
	public ArrayList<Pizza> findAllPizzas() {
		return pizzaArray;
	}
	
	public void deletePizza(String codePizza) {
		
		for(Pizza currentPizza : pizzaArray) {
			if(currentPizza.code.equals(codePizza)) {
				pizzaArray.remove(currentPizza);
				return;
			}
		}
		System.out.println("Attention : aucune pizza avec le code donné. Rien n'a été supprimé.");
	}

	
	public boolean pizzaExists(String codePizza) {
		for(Pizza currentPizza : pizzaArray) {
			if(currentPizza.code.equals(codePizza)) {
				return true;
			}
		}
		return false;
	}
	
	public void saveNewPizza(Pizza pizza) {
		if(!this.pizzaExists(pizza.code)) {
			pizzaArray.add(pizza);
		}
	}
	
	public void updatePizza(String codePizza, Pizza pizza) {
		Pizza pizzaToModify = this.findPizzaByCode(codePizza);
		int indexOfPizza = pizzaArray.indexOf(pizzaToModify);
		pizzaArray.set(indexOfPizza, pizza);
	}
	
	public Pizza findPizzaByCode(String codePizza) {
		for(Pizza currentPizza : pizzaArray) {
			if(currentPizza.code.equals(codePizza)) {
				return currentPizza;
			}
		}
		System.out.println("Erreur : aucune pizza avec le code donné.");
		return null;
	}
}
