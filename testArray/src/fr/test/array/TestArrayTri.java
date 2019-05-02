package fr.test.array;
import java.util.Arrays;

public class TestArrayTri {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,15, -3, 0, 8,7,4,-2,28,7,-1,17,2,3,0,14,-4};
		//sorting array and display it
		Arrays.sort(array);
		System.out.println("=====sorted array=====");

		for(int i=0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
