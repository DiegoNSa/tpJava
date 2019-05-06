package fr.test.fibonacci;

import java.util.Scanner;

public class TestFibonacci {

	//iterative version of fibonacci sequence
	public static int fibo(int index) {
		int a = 0; //previous value
		int b = 1; //current value
		if(index == 0) {
			return a;
		}
		for(int n = 1; n < index;n++) {
			//update the fibonacci values.
			b=a+b; //b' = a + b
			a = b-a; //a' = b'-a = a + b - a = b
		}
		return b; //return the fibonacci value for given rank
	}
	
	public static void main(String[] args) {
		
		Scanner questionUser = new Scanner(System.in);

		System.out.println("rank :");
		int chosenRank = questionUser.nextInt();
		System.out.println("fibo(" + chosenRank + ")=" + TestFibonacci.fibo(chosenRank) );

	}

}
