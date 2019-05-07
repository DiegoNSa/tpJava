package fr.pizzeria.controler;

import java.util.Scanner;

import fr.pizzeria.mem.PizzaDao;
import fr.pizzeria.model.Pizza;
import java.util.ArrayList;

public abstract class MenuService {
		
		protected static Scanner questionUser = new Scanner(System.in);
 
		protected static double inputDouble() {
			double newPrice = 0.0;
			boolean success = false;
			while(!success) {
				try {
					newPrice = questionUser.nextDouble();
					success = true;
				}catch(java.util.InputMismatchException ex) {
					System.out.println("format invalide. retapez la valeur ");
					questionUser.next();
				}
			}
			return newPrice;
		}
		
		protected static int inputInt() {
			int newPrice = 0;
			boolean success = false;
			while(!success) {
				try {
					newPrice = questionUser.nextInt();
					success = true;
				}catch(java.util.InputMismatchException ex) {
					System.out.println("format invalide (exemple : '10,75'). retapez la valeur ");
					questionUser.next();
				}
			}
			return newPrice;
		}
		
		//check if the code is unique, if not ask again for a code until it is unique
		protected static String inputCode(ArrayList<Pizza> pizzaArray) {
			return inputCode(pizzaArray,null);
		}
		
		//check if the code is unique, if not ask again for a code until it is unique
		protected static String inputCode(ArrayList<Pizza> pizzaArray, String authorizedCode) {
			boolean pizzaIsUnique = false;
			String newCode = "";
			while(!pizzaIsUnique) {
				System.out.println("Veuillez saisir le code");
				 newCode = questionUser.next();

				pizzaIsUnique = true;
				
				for(Pizza currentPizza : pizzaArray) {
					if(newCode.contentEquals(currentPizza.code) && !(authorizedCode != null && newCode.contentEquals(authorizedCode))) {
						pizzaIsUnique = false;
						break;
					}
				}
				
				if(!pizzaIsUnique) {
					System.out.println("Attention ! le code " + newCode + " est déja attribué a une autre pizza");
				}
			}
			return newCode;
		}
	
	public abstract void executerUC(PizzaDao pizzaDataBase);
}
