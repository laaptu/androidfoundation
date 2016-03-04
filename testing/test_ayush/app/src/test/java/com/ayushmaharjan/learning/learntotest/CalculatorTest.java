package com.ayushmaharjan.learning.learntotest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void testSum() throws Exception {
        assertEquals(6d, mCalculator.sum(4d,2d), 0);
    }

    @Test
    public void testSubtract() throws Exception {
        assertEquals(2d, mCalculator.subtract(4d, 2d), 0);
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