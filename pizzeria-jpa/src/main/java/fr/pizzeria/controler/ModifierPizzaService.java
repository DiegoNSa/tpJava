package fr.pizzeria.controler;
import fr.pizzaria.exception.*;
import fr.pizzeria.mem.IPizzaDao;
import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.CategoryPizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.Validator;

public class ModifierPizzaService extends MenuService {

	public void executerUC(IPizzaDao pizzaDataBase) throws PizzaException{
		System.out.println("Veuillez choisir le code de la pizza � modifier");
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

		System.out.println("Veuillez saisir la categorie");
		CategoryPizza.enumerate();
		String modifiedCategoryName = questionUser.next();
		CategoryPizza modifiedCategory = CategoryPizza.getCategory(modifiedCategoryName);
		if(modifiedCategory == null) {
			throw new UpdatePizzaException("categorie " + modifiedCategoryName + " does not exists");
		}
		
		Pizza newPizza = new Pizza();
		newPizza.setCode(modifiedCode);
		newPizza.setLibelle(modifiedCode);
		newPizza.setPrix(modifiedPrice);
		newPizza.setCategory(modifiedCategory);
		try {
			Validator.validate(newPizza);
		}catch(Exception ex) {
			throw new SavePizzaException(ex.getMessage() + " - prix invalide");
		}
		
		pizzaDataBase.updatePizza(codeToFind, newPizza);
		/*pizzaToModify.code = newPizza.code;
		pizzaToModify.libelle = newPizza.libelle;
		pizzaToModify.prix = newPizza.prix;
		pizzaToModify.category = newPizza.category;*/
		

	}

}
