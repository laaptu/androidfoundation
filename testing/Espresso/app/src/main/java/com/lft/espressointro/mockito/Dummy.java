package com.lft.espressointro.mockito;

/**
 * Created by laaptu on 3/16/16.
 */
public class Dummy {

    private Dummy(){

    }

    public int getDummyValue() {
        return dummyValue;
    }

    public void setDummyValue(int dummyValue) {
        this.dummyValue = dummyValue;
    }

    private int dummyValue;

}
