package fr.pizzaria.factory;

import fr.pizzeria.controler.*;
public class MenuServiceFactory {
	
	public MenuService getService(int serviceCode) {
		switch(serviceCode) {
			case 1:
				return new ListerPizzaService();
			case 2:
				return new AjouterPizzaService();
			case 3:
				return new ModifierPizzaService();
			case 4 :
				return new SupprimerPizzaService();
			case 5 :
				return new ReinitialisePizzaService();

			default:
				return new InvalideService();
		}
	}
}
