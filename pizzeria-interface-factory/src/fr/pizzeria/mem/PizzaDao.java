package fr.pizzeria.mem;

import fr.pizzeria.model.Pizza;

public class PizzaDao implements IPizzaDao {
	private Pizza[] pizzaArray;
	
	public PizzaDao() {
		pizzaArray = new Pizza[8];
		pizzaArray[0] = new Pizza("PEP","Pépéroni",12.5);
		pizzaArray[1] = new Pizza("MAR","Margherita",14.0);
		pizzaArray[2] = new Pizza("REIN","La Reine",11.5);
		pizzaArray[3] = new Pizza("FRO","La 3 fromage",12.0);
		pizzaArray[4] = new Pizza("CAN","La cannibale",12.5);
		pizzaArray[5] = new Pizza("SAV","La savoyarde",13.0);
		pizzaArray[6] = new Pizza("ORI","L'orientale",13.5);
		pizzaArray[7] = new Pizza("IND","L'indienne",14.0);
	}
	//increase the size of an array
	private static Pizza[] increaseSize(Pizza[] oldArray,int newSize) {
		Pizza[] newArray = new Pizza[newSize];
	
		//copy content of array
		for(int i = 0;i < oldArray.length;i++) {
			newArray[i]=oldArray[i]; 
		}
		return newArray;
	}	
	
	
	
	
	public Pizza[] findAllPizzas() {
		return pizzaArray;
	}
	
	public void deletePizza(String codePizza) {
		for(int i = 0;i < pizzaArray.length;i++) {
			Pizza currentPizza = pizzaArray[i];

			if(currentPizza != null && currentPizza.code.equals(codePizza)){
				pizzaArray[i] = null;
				return;
			}
		}
		System.out.println("Attention : aucune pizza avec le code donné. Rien n'a été supprimé.");
	}

	
	public boolean pizzaExists(String codePizza) {
		for(int i = 0;i < pizzaArray.length;i++) {
			if(pizzaArray[i] != null && pizzaArray[i].code.equals(codePizza)){
				return true;
			}
		}
		return false;
	}
	
	public void saveNewPizza(Pizza pizza) {
		if(!this.pizzaExists(pizza.code)) {
			if(pizza.id >= pizzaArray.length) {
				pizzaArray = increaseSize(pizzaArray,(pizza.id + 1)*2);
			}
			if(pizzaArray[pizza.id] == null) {
				pizzaArray[pizza.id] = pizza;
			}
			else {
				System.out.println("Erreur : Une pizza d'id identique existe déja.");
			}
			
		}
	}
	
	public void updatePizza(String codePizza, Pizza pizza) {
		for(int i = 0;i < pizzaArray.length;i++) {
			Pizza currentPizza = pizzaArray[i];

			if(currentPizza != null &&  currentPizza.code.equals(codePizza)){
				pizzaArray[i] = pizza;
				return;
			}
		}
	}
	
	public Pizza findPizzaByCode(String codePizza) {
		for(int i = 0;i < pizzaArray.length;i++) {
			Pizza currentPizza = pizzaArray[i];

			if(currentPizza != null && currentPizza.code.equals(codePizza)){
				return pizzaArray[i];
			}
		}
		System.out.println("Erreur : aucune pizza avec le code donné.");
		return new Pizza();
	}
}
