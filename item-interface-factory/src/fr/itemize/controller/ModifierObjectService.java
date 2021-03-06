package fr.itemize.controller;

import fr.itemize.mem.*;
import fr.itemize.model.*;

public class ModifierObjectService extends MenuService {

	public void executerUC(GlobalDao pizzaDataBase) {
		System.out.println("Veuillez choisir le code de la pizza � modifier");
		String codeToFind;

		// look for the given code in the list
		do {
			System.out.println("Code de la pizza a modifier");

			codeToFind = questionUser.next();
		} while (!pizzaDataBase.pizzaExists(codeToFind));
		Pizza pizzaToModify = pizzaDataBase.findPizzaByCode(codeToFind);

		// ask for the new values
		String modifiedCode = inputCode(pizzaDataBase.findAllPizzas(), codeToFind);
		System.out.println("Veuillez saisir le nom (sans espace)");
		String modifiedName = questionUser.next();
		System.out.println("Veuillez saisir le prix");
		double modifiedPrice = inputDouble();

		pizzaToModify.code = modifiedCode;
		pizzaToModify.libelle = modifiedName;
		pizzaToModify.prix = modifiedPrice;
	}

}
