package com.ayushmaharjan.learning.learntotest;

public class Calculator {

    public double sum = 0;

    public double sum(double a, double b) {
        sum = a + b;
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double divide(double a, double b) {
        return a / b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }
}
