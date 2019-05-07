package fr.itemize.controller;

import java.util.ArrayList;
import fr.itemize.mem.*;
import fr.itemize.model.*;

public class AjouterTypeService extends MenuService {
	public void executerUC(GlobalDao globalDataBase) {
		ArrayList<String> parameterList = new ArrayList<String>();
		
		System.out.println("Ajout d'un nouveau type");
		String newTypeName = "";
		do {
			newTypeName = questionUser.next();
		}while(globalDataBase.myObjectDao.typeExists(newTypeName));
		
		
		
		while(true) {
			System.out.println("veuillez entrer un nouveau nom de parametre (/stop pour arreter)");
			String newParameter = questionUser.next();
			if(newParameter.equals("/stop")) {
				break;
			}
			if(parameterList.indexOf(newParameter) == -1) {
				parameterList.add(newParameter);
			}
		}
			
		//create the pizza and add it to the list
		FactoryType newType = new FactoryType(newTypeName, parameterList);
		
		//create the pizza and add it to the list
		globalDataBase.myObjectDao.addNewType(newType);
	}
}
