package fr.pizzeria.controler;

import fr.pizzaria.exception.PizzaException;
import fr.pizzeria.bdd.*;
import fr.pizzeria.mem.*;

public class ReinitialisePizzaService extends MenuService {

	public void executerUC(IPizzaDao pizzaDataBase) throws PizzaException{
		BddManager.resetDB();
		PizzaDao defaultDao = new PizzaDao();
		BddManager.importDB(defaultDao);
	}
}