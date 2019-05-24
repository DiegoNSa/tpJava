package fr.pizzeria.controler;
import fr.pizzaria.exception.*;

import java.util.Scanner;

import fr.pizzeria.mem.*;
import fr.pizzeria.model.Pizza;
import java.util.ArrayList;

public abstract class MenuService {
		
		protected Scanner questionUser = new Scanner(System.in);
 
		protected void flush() {
			while(questionUser.hasNext()) {
				System.out.print("flushing" + questionUser.next());
			}
		}
		
		protected double inputDouble() {
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
		
		protected int inputInt() {
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
		protected  String inputCode(ArrayList<Pizza> pizzaArray) {
			return inputCode(pizzaArray,null);
		}
		
		//check if the code is unique, if not ask again for a code until it is unique
		protected  String inputCode(ArrayList<Pizza> pizzaArray, String authorizedCode) {
			boolean pizzaIsUnique = false;
			String newCode = "";
			while(!pizzaIsUnique) {
				System.out.println("Veuillez saisir le code");
				 newCode = questionUser.next();

				pizzaIsUnique = true;
				
				for(Pizza currentPizza : pizzaArray) {
					if(newCode.contentEquals(currentPizza.getCode()) && !(authorizedCode != null && newCode.contentEquals(authorizedCode))) {
						pizzaIsUnique = false;
						break;
					}
				}
				
				if(!pizzaIsUnique) {
					System.out.println("Attention ! le code " + newCode + " est d�ja attribu� a une autre pizza");
				}
			}
			return newCode;
		}
	
	public abstract void executerUC(IPizzaDao pizzaDataBase) throws PizzaException;
}
