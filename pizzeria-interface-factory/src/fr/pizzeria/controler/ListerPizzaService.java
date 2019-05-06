package fr.pizzeria.controler;
import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzaService extends MenuService {

	public void executerUC(PizzaDao pizzaDataBase) {
		System.out.println("Liste des Pizza");
		for(int i=0;i < pizzaDataBase.findAllPizzas().length; i++) {
			Pizza currentPizza = pizzaDataBase.findAllPizzas()[i];
			if(pizzaDataBase.findAllPizzas()[i] != null) {
				System.out.println("Pizza n°" + currentPizza.id + ": " + currentPizza.code + " -> " + currentPizza.libelle + "(" + currentPizza.prix + "€)");
			}
		}
	}

}
