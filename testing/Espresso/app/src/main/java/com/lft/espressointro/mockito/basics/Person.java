package com.lft.espressointro.mockito.basics;

/**
 * Created by laaptu on 3/14/16.
 */
public class Person {

    public String address;
    private int age;

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

    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public Person() {

    }

    public void manipulateAgeNName(int age) {
        manipulateAgeNName(age, String.valueOf(age));
    }

    public void manipulateAgeNName(String name) {
        manipulateAgeNName((int) (Math.random() * 100), name);
    }

    public void manipulateAgeNName(int age, String name) {
        setAge(age);
        setName(name);
    }

    public int addAge(int age) {
        this.age += age;
        System.out.println("add age =" + age);
        return this.age;
    }

    public void addName(String name) {
        if (this.name == null)
            this.name = "NULL ";
        this.name += name;
    }

}
