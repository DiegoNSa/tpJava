package fr.array.insertion;

import java.util.Scanner;

public class UserArray {
	//increase array size by doubling it size when overflowing
	public static int[] increaseSize(int[] oldArray) {
		int[] newArray = new int[oldArray.length * 2];
		//copy content of array
		for(int i = 0;i < oldArray.length;i++) {
			newArray[i]=oldArray[i];
		}
		return newArray;
	}
	
	public static void main(String[] args) {
		Scanner questionUser = new Scanner(System.in);

		boolean exit = false;
		int[] mutableArray = new int[2];
		int arraySize = 0;
		while(!exit) {
			System.out.println("1 - add number.");
			System.out.println("2 - display numbers.");
			System.out.println("3 - exit.");
			
			int chosenOption = questionUser.nextInt();
			switch(chosenOption) {
			case 1 :
				System.out.println("number to add :");
				int newNumber = questionUser.nextInt();
				if(arraySize == mutableArray.length) {
					mutableArray = UserArray.increaseSize(mutableArray);
				}
				mutableArray[arraySize] = newNumber;
				arraySize++;
				break;
			case 2:
				for(int i = 0; i < arraySize;i++) {
					System.out.println(mutableArray[i]);
				}
				break;
			case 3:
				exit = true;
				break;
			default:
				System.out.println("invalid option.");
				break;
			}
		}
	}

}
