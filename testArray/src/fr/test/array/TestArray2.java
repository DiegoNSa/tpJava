package fr.test.array;

public class TestArray2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,15, -3, 0, 8,7,4,-2,28,7,4,-2,3,0,14,-4,0,0,1};
		
		//compute mean value of array
		System.out.println("=========mean value==========");
		double meanValue = 0;
		for(int i = 0; i < array.length; i++) {
			meanValue += array[i];
		}
		meanValue = meanValue / array.length;
		System.out.println("mean value = " + meanValue);
		
		//Search the index of value 15 in array 
		System.out.println("=========look for 15==========");
		int indexOf15 = -1;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 15) {
				indexOf15 = i;
				break;
			}
		}
		System.out.println("array[" + indexOf15 +"] = 15");

		//compute the number of repeating numbers
		System.out.println("=========repeating numbers==========");
		int numberOfRepeatedValue = 0;
		boolean[] alreadyChecked = new boolean[array.length]; //table of already checked index
		boolean repeatingNumberSpoted = false;
		for(int i = 0; i < array.length - 1; i++) {
			repeatingNumberSpoted = false; //variable to check if we already spot the same number on the way
			if(alreadyChecked[i]) {
				continue; //if we have already checked the value we can continue
			}
			for(int j = i+1; j < array.length; j++) {
				if(array[i] == array[j]) {
					alreadyChecked[j] = true;
					if(repeatingNumberSpoted) {
						continue;
					}
					repeatingNumberSpoted = true;
					numberOfRepeatedValue += 1;
					System.out.println("repeating number " + array[i]);
				}
			}
		}
		System.out.println("there is " + numberOfRepeatedValue + " repeating number" + ((numberOfRepeatedValue>1)?"s":"")  );

	}

}
