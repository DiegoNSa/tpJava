package fr.pizzeria.controler;
import fr.pizzaria.exception.*;
import fr.pizzeria.mem.IPizzaDao;
import fr.pizzeria.mem.PizzaDao;

public class SupprimerPizzaService extends MenuService {

	public void executerUC(IPizzaDao pizzaDataBase) throws PizzaException {
		System.out.println("Supprimer Pizza");

		String codeToFind;
		System.out.println("Code de la pizza a supprimer");
		codeToFind = questionUser.next();
		if(pizzaDataBase.pizzaExists(codeToFind)) {
			pizzaDataBase.deletePizza(codeToFind);

		}
		else {
			throw new DeletePizzaException("Pizza not found");
		}
	}


}
