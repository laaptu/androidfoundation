package com.lft.realmtest.model;

import io.realm.RealmObject;

/**
 * Created by laaptu on 2/10/16.
 */
public class ThirdModel extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
