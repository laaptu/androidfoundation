package com.lft.realmtest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by laaptu on 2/10/16.
 */
public class Person extends RealmObject {
    private String name;
    private int age;
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
