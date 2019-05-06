package fr.itemize.controller;
import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerObjectService extends MenuService {

	public void executerUC(GlobalDao pizzaDataBase) {
		System.out.println("Liste des Pizza");
		for(int i=0;i < pizzaDataBase.findAllPizzas().length; i++) {
			Pizza currentPizza = pizzaDataBase.findAllPizzas()[i];
			if(pizzaDataBase.findAllPizzas()[i] != null) {
				System.out.println("Pizza n°" + currentPizza.id + ": " + currentPizza.code + " -> " + currentPizza.libelle + "(" + currentPizza.prix + "€)");
			}
		}
	}

}
