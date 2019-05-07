package fr.pizzeria.controler;
import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzaService extends MenuService {

	public void executerUC(PizzaDao pizzaDataBase) {
		System.out.println("Liste des Pizza");
		for(Pizza currentPizza : pizzaDataBase.findAllPizzas()) {
			System.out.println("Pizza n°" + currentPizza.id + ": " + currentPizza.code + " -> " + currentPizza.libelle + "(" + currentPizza.prix + "€)");
		}		
	}

}
