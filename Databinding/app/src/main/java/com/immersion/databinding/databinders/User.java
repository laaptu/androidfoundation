package com.immersion.databinding.databinders;

/**
 * Created by laaptu on 1/4/16.
 */
public class User {

    public String firstName, lastName;
    public boolean isFriend = true;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
