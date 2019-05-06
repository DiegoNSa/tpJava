package fr.pizzeria.controler;
import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;


public class AjouterPizzaService extends MenuService {

	public void executerUC(PizzaDao pizzaDataBase) {
		System.out.println("Ajout d'une nouvelle pizza");
		String newCode = inputCode(pizzaDataBase.findAllPizzas());
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
