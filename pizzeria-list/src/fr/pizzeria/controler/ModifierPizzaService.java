package fr.pizzeria.controler;

import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	public void executerUC(PizzaDao pizzaDataBase) {
		System.out.println("Veuillez choisir le code de la pizza à modifier");
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
