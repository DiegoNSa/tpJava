package fr.pizzeria.controler;
import fr.pizzeria.mem.PizzaDao;

public class SupprimerPizzaService extends MenuService {

	public void executerUC(PizzaDao pizzaDataBase) {
		System.out.println("Supprimer Pizza");

		String codeToFind;
		do {
			System.out.println("Code de la pizza a supprimer");

			codeToFind = questionUser.next();
		}while(!pizzaDataBase.pizzaExists(codeToFind));
		pizzaDataBase.deletePizza(codeToFind);
	}


}
