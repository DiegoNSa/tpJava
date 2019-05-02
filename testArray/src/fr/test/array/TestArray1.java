package fr.test.array;

public class TestArray1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("========ascending order========");
		int[] array = {1,15, -3, 0, 8,7,4,-2,28,7,-1,17,2,3,0,14,-4};
		//display array number in ascending order
		for(int i=0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		System.out.println("=======descending order=========");
		//display array number in descending order
		for(int i=array.length - 1; i >= 0; i--) {
			System.out.println(array[i]);
		}
		System.out.println("=========only > 3=======");

		//display array number greater than 3
		for(int i=0; i < array.length; i++) {
			if(array[i] <= 3) {
				continue;
			}
			System.out.println(array[i]);
		}
		System.out.println("========only even========");

		//display array number that are even
		for(int i=0; i < array.length; i++) {
			if(array[i] % 2 == 0) {
				System.out.println(array[i]);
			}
		}
		System.out.println("========max value========");

		//display maximum value of array
		int maxValue = array[0];
		for(int i=0; i < array.length; i++) {
			if(array[i] > maxValue) {
				maxValue = array[i];
			}
		}
		System.out.println("greatest value : " +maxValue);
		System.out.println("========min value========");

		//display maximum value of array
		int minValue = array[0];
		for(int i=0; i < array.length; i++) {
			if(array[i] < minValue) {
				minValue = array[i];
			}
		}
		System.out.println("lowest value : " +minValue);
		System.out.println("================");

	}
}
