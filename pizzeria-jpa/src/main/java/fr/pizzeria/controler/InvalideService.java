package fr.pizzeria.controler;
import fr.pizzaria.exception.*;
import fr.pizzeria.mem.IPizzaDao;
import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;

public class InvalideService extends MenuService {

	public void executerUC(IPizzaDao pizzaDataBase) throws PizzaException {
		System.out.println("Erreur : Option Invalide");
	}

}
