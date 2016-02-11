package com.lft.realmtest.model;

import io.realm.RealmObject;

/**
 * Created by laaptu on 2/10/16.
 */
public class FourthModel extends RealmObject {
    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
