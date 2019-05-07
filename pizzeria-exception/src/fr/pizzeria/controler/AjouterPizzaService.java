package fr.pizzeria.controler;
import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;
import fr.pizzaria.exception.*;


public class AjouterPizzaService extends MenuService {

	public void executerUC(PizzaDao pizzaDataBase) throws PizzaException{
		System.out.println("Ajout d'une nouvelle pizza");
		String newCode = questionUser.next();
		if(pizzaDataBase.pizzaExists(newCode)) {
			throw new UpdatePizzaException("Pizza " + newCode + " already exists");

		}
		System.out.println("Veuillez saisir le nom (sans espace)");
		String newName = questionUser.next();
		System.out.println("Veuillez saisir le prix");
		double newPrice = inputDouble();
		
		//create the pizza and add it to the list
		Pizza newPizza = new Pizza(newCode, newName, newPrice);
		
		//create the pizza and add it to the list
		pizzaDataBase.saveNewPizza(newPizza);
	}


}
