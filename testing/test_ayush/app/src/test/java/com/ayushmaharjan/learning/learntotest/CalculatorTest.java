package com.ayushmaharjan.learning.learntotest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator mCalculator =new Calculator();

    @Before
    public void setUp() throws Exception {
        //mCalculator = new Calculator();
    }

    //http://stackoverflow.com/questions/5686755/meaning-of-epsilon-argument-of-assertequals-for-double-values
    //http://stackoverflow.com/questions/5939788/junit-assertequalsdouble-expected-double-actual-double-epsilon
    @Test
    public void testSum() throws Exception {
        assertEquals(6d, mCalculator.sum(4d,2d), 0);
        System.out.println("SUm0 ="+mCalculator.sum);

        assertEquals(8d, mCalculator.sum(4d,4d), 0);
        System.out.println("SUm1 ="+mCalculator.sum);
        /**
         * If diff is exactly 0, then no matter,
         * what you put in delta or epsilon,
         * but if you want to */
        assertEquals(12.456,12.455,0.001);
        assertEquals(12.456,12.456,30);
        double ds= 5d/3d;
        //Math.abs(expected - actual) < epsilon
        assertEquals(1.66,ds,0.1);
    }

    @Test
    public void testSubtract() throws Exception {
        assertEquals(2d, mCalculator.subtract(4d, 2d), 0);
        System.out.println("SUm2 ="+mCalculator.sum);
    }

    @Test
    public void testDivide() throws Exception {
        assertEquals(2d, mCalculator.divide(4d, 2d), 0);
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(8d, mCalculator.multiply(4d,2d), 0);
    }
}