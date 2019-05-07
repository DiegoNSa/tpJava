package fr.pizzaria.console;
import fr.pizzaria.exception.*;
import java.util.Scanner;

import fr.pizzaria.factory.MenuServiceFactory;
import fr.pizzeria.controler.*;
import fr.pizzeria.mem.PizzaDao;

public class PizzariaAdminConsoleApp {
	private static Scanner questionUser = new Scanner(System.in);
	private static MenuServiceFactory menuFactory = new MenuServiceFactory();
	private static PizzaDao pizzaDataBase = new PizzaDao();
	
	private static int inputInt() {
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
	
	
	
	public static void main(String[] args) {
		boolean exit = false;
		while(!exit) {
			System.out.println("*****Pizzeria Administration*****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizzas");
			System.out.println("99. Sortir");
			
			int chosenOption = inputInt();
			if(chosenOption == 99) {
				exit = true;
			}
			else {
				try{
					menuFactory.getService(chosenOption).executerUC(pizzaDataBase);
				}catch(PizzaException ex) {
					System.out.println("opération invalide. Cause : " + ex.getMessage());
				}
			}

		}
		
	}

}
