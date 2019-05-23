package fr.pizzeria.controler;
import fr.pizzeria.mem.IPizzaDao;
import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;
import fr.pizzaria.exception.*;

public class ListerPizzaService extends MenuService {

	public void executerUC(IPizzaDao pizzaDataBase) throws PizzaException{
		System.out.println("Liste des Pizza");
		for(Pizza currentPizza : pizzaDataBase.findAllPizzas()) {
			System.out.println(currentPizza.toString());
		}		
	}

}
