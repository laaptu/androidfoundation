package com.lft.espressointro.unittest;

import java.util.Arrays;

/**
 * Created by laaptu on 3/1/16.
 */
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
