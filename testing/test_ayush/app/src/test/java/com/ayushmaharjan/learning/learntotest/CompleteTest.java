package com.ayushmaharjan.learning.learntotest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorTest.class,
        ExampleUnitTest.class,
        EmailValidatorTest.class,
        AssertTests.class,
        PrimeNumberCheckerTest.class,
        SharedPreferencesHelperTest.class})
public class CompleteTest {
}
