package com.lft.realmtest.model;

import io.realm.RealmObject;

/**
 * Created by laaptu on 2/10/16.
 */
public class User extends RealmObject {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
