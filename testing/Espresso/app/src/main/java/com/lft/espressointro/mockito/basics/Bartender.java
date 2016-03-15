package com.lft.espressointro.mockito.basics;

/**
 * Created by laaptu on 3/15/16.
 */
public class Bartender extends Person {
    public Bartender(String name, int age) {
        super(name, age);
    }

    public enum Drink {
        CockTail, RumPunch, Mojito, Whiskey;
    }

    public Drink askForRandomDrink() {
        System.out.println("Calling for a drink");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final int random = (int) (Math.random() * 3);
        switch (random) {
            case 1:
                return Drink.CockTail;
            case 2:
                return Drink.RumPunch;
            case 3:
                return Drink.Mojito;
            default:
                return Drink.Whiskey;

        }
    }
}
