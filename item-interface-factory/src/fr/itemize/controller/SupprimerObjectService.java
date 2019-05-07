package fr.itemize.controller;
import fr.itemize.mem.*;

public class SupprimerObjectService extends MenuService {

	public void executerUC(GlobalDao pizzaDataBase) {
		System.out.println("Supprimer Pizza");

		String codeToFind;
		do {
			System.out.println("Code de la pizza a supprimer");

			codeToFind = questionUser.next();
		}while(!pizzaDataBase.pizzaExists(codeToFind));
		pizzaDataBase.deletePizza(codeToFind);
	}


}
