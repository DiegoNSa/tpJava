package fr.itemize.service;

import fr.pizzeria.controler.*;
public class MenuServiceItemFactory {
	
	public MenuServiceFactory getService(int serviceCode) {
		switch(serviceCode) {
			case 1:
				return new ListerObjectService();
			case 2:
				return new AjouterObjectService();
			case 3:
				return new ModifierObjectService();
			case 4 :
				return new SupprimerObjectService();
			default:
				return new InvalideService();
		}
	}
}
