package fr.pizzeria.controler;

import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;

public class InvalideService extends MenuService {

	public void executerUC(PizzaDao pizzaDataBase) {
		System.out.println("Erreur : Option Invalide");
	}

}
