package com.lft.realmtest.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by laaptu on 2/10/16.
 */
public class Person extends RealmObject {
    private String name;
    private int age;
    @PrimaryKey
    private int id;

    //this indicates that this field or to say column can be null
    @Ignore
    private int ignoreField;


    private int phNum;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhNum() {
        return phNum;
    }

    public void setPhNum(int phNo) {
        this.phNum = phNo;
    }

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
