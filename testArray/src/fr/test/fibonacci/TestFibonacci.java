package fr.test.fibonacci;

import java.util.Scanner;

public class TestFibonacci {

	//iterative version of fibonacci sequence
	public static int fibo(int index) {
		int a = 0;
		int b = 1;
		if(index == 0) {
			return a;
		}
		for(int n = 1; n < index;n++) {
			b=a+b;
			a = b-a;
		}
		return b;
	}
	
	public static void main(String[] args) {
		Scanner questionUser = new Scanner(System.in);

		System.out.println("rank :");
		int chosenRank = questionUser.nextInt();
		System.out.println("fibo(" + chosenRank + ")=" + TestFibonacci.fibo(chosenRank) );

	}

}
