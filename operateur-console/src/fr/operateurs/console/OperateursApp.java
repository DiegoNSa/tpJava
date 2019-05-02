package fr.operateurs.console;

import java.util.Scanner;

public class OperateursApp {

	public static void main(String[] args) {
		Scanner questionUser = new Scanner(System.in);
		// ask for the first number
		System.out.println("*****  Application Opérateurs *****");
		System.out.println("Veuiller saisir le premier nombre ...");
		int firstNumber = questionUser.nextInt();

		// ask for the second number
		System.out.println("Veuiller saisir le second nombre ...");
		int secondNumber = questionUser.nextInt();
		
		System.out.println(firstNumber + "+" + secondNumber + " = " + (firstNumber + secondNumber));
		System.out.println(firstNumber + "-" + secondNumber + " = " + (firstNumber - secondNumber));
		System.out.println(firstNumber + "*" + secondNumber + " = " + (firstNumber * secondNumber));
		System.out.println(firstNumber + "/" + secondNumber + " = " + (double)((double)firstNumber / (double)secondNumber));
		System.out.println(firstNumber + "%" + secondNumber + " = " + (firstNumber % secondNumber));

	}

}
