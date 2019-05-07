package fr.itemize.service;

import fr.itemize.controller.*;
public class MenuServiceFactory {
	
	public MenuService getService(int serviceCode) {
		switch(serviceCode) {
			case 1:
				return new ListerTypeService();
			case 2:
				return new AjouterTypeService();
			case 3:
				return new AjouterObjectService();
			case 4 :
				return new ListerObjectService();
			default:
				return new InvalideService();
		}
	}
}
