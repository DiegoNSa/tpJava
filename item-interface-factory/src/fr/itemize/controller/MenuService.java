
package fr.itemize.controller;

import java.util.Scanner;

import fr.itemize.mem.*;
import fr.itemize.model.*;

public abstract class MenuService {
		
		protected static Scanner questionUser = new Scanner(System.in);
 
		//check if the code is unique, if not ask again for a code until it is unique
		protected static String inputCode(FactoryItem[] pizzaArray) {
			return inputCode(pizzaArray,null);
		}
		
		//check if the code is unique, if not ask again for a code until it is unique
		protected static String inputCode(FactoryItem[] pizzaArray, String authorizedCode) {
			boolean pizzaIsUnique = false;
			String newCode = "";
			while(!pizzaIsUnique) {
				System.out.println("Veuillez saisir le code");
				 newCode = questionUser.next();

				pizzaIsUnique = true;
				for(int i = 0; i < pizzaArray.length; i++) {
					FactoryItem currentPizza = pizzaArray[i];
					//check if the pizza exist
					if(currentPizza != null) {
						//check if the code fits
						if(newCode.contentEquals(currentPizza.code) && !(authorizedCode != null && newCode.contentEquals(authorizedCode))) {
							pizzaIsUnique = false;
							break;
						}
					}
				}
				if(!pizzaIsUnique) {
					System.out.println("Attention ! le code " + newCode + " est déja attribué a une autre pizza");
				}
			}
			return newCode;
		}
	
	public abstract void executerUC(GlobalDao pizzaDataBase);
}