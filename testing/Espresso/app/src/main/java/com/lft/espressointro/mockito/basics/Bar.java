package com.lft.espressointro.mockito.basics;

/**
 * Created by laaptu on 3/15/16.
 */
public class Bar {
    private Bartender bartender;

    public Bar(Bartender bartender) {
        this.bartender = bartender;
    }

    public Bartender.Drink buyDrink() {
        return bartender.askForRandomDrink();
    }
}
