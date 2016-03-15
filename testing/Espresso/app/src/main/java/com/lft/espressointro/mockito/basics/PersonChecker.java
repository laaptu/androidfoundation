package com.lft.espressointro.mockito.basics;

/**
 * Created by laaptu on 3/14/16.
 */
public class PersonChecker {

    public static int AGE = 20;
    public static String NAME = "John";

    public boolean checkPerson(Person person) {
        return person.getAge() == 20 && person.getName().contains(NAME);
    }
}
