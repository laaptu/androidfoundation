package junit;

import java.util.Arrays;

public class SimpleAddition {

	public int addTwoNum(int a, int b) {
		return a + b;
	}

	public int multiplyNum(int a, int b) {
		return a * b;
	}

	public int[] sortArrays(int[] unsortedArray) {
		Arrays.sort(unsortedArray);
		return unsortedArray;
	}
}
