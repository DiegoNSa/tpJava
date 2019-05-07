package fr.itemize.controller;
import java.util.Hashtable;

import fr.itemize.mem.*;
import fr.itemize.model.*;

public class AjouterObjectService extends MenuService {

	public void executerUC(GlobalDao globalDataBase) {
		System.out.println("type d'objet a ajouter");
		String newObjectType = questionUser.next();
		if(!globalDataBase.myObjectDao.typeExists(newObjectType)) {
			System.out.println("le type d'objet n'existe pas. ajoutez le avant d'utiliser");
			return;
		}
		System.out.println("entrez le code");
		String newCode = questionUser.next();
		if(globalDataBase.myItemDao.ItemExists(newCode)) {
			return;
		}
		
		
		FactoryType typeOfObjectToCreate = globalDataBase.myObjectDao.getType(newObjectType);
		Hashtable<String,String> tableOfValues = new Hashtable<String,String>();
		for(String varName : typeOfObjectToCreate.variables) {
			System.out.println("entrez la valeur pour " +varName);
			String variableValue = questionUser.next();
			tableOfValues.put(varName, variableValue);
			
		}
		
		FactoryObject newObject = new FactoryObject(typeOfObjectToCreate,tableOfValues);
		FactoryItem newItem = new FactoryItem(newCode,newObject);

		globalDataBase.myItemDao.addNewItem(newItem);
		/*System.out.println("Veuillez saisir le code");
		String newName = questionUser.next();
		System.out.println("Veuillez saisir le prix");
		double newPrice = inputDouble();
		
		//create the pizza and add it to the list
		Pizza newPizza = new Pizza(newCode, newName, newPrice);
		
		//create the pizza and add it to the list
		pizzaDataBase.saveNewPizza(newPizza);*/
	}


}
