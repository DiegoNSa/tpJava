package fr.itemize.console;

import java.util.Scanner;

import fr.pizzaria.factory.MenuServiceItemFactory;
import fr.pizzeria.mem.*;

public class ItemizeAdminConsoleApp {
	private static Scanner questionUser = new Scanner(System.in);
	private static MenuServiceItemFactory menuFactory = new MenuServiceItemFactory();
	private static GlobalDao globalDataBase = new GlobalDao();
	
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
			System.out.println("1. Lister les objets");
			System.out.println("2. Ajouter un nouvel objet");
			System.out.println("3. Mettre à jour un objet");
			System.out.println("4. Supprimer un objet");
			System.out.println("99. Sortir");
			
			int chosenOption = inputInt();
			if(chosenOption == 99) {
				exit = true;
			}
			else {
				menuFactory.getService(chosenOption).executerUC(globalDataBase);
			}

		}
		
	}

}
