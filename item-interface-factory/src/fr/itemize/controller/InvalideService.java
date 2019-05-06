package fr.itemize.controller;

import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;

public class InvalideService extends MenuServiceFactory {

	public void executerUC(GlobalDao pizzaDataBase) {
		System.out.println("Erreur : Option Invalide");
	}

}
