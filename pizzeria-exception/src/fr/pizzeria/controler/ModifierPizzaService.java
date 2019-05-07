package fr.pizzeria.controler;
import fr.pizzaria.exception.*;

import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	public void executerUC(PizzaDao pizzaDataBase) throws PizzaException{
		System.out.println("Veuillez choisir le code de la pizza à modifier");
		String codeToFind;

		// look for the given code in the list
			System.out.println("Code de la pizza a modifier");

			codeToFind = questionUser.next();
		
		if(!pizzaDataBase.pizzaExists(codeToFind)) {
			throw new UpdatePizzaException("Pizza not found");

		}
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
