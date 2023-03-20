package com.example.healthly;

public class User {
    public String mail;
    public String name;
    public String weight;
    public String tall;
    public String age;

    public User (){}

    public User(String mail, String name, String weight, String tall, String age) {
        this.mail = mail;
        this.name = name;
        this.weight = weight;
        this.tall = tall;
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }

    public String getTall() {
        return tall;
    }

    public String getAge() {
        return age;
    }
}
