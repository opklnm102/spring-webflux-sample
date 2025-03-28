package com.example.demo.car.user;

public sealed class User permits Customer, Store, Admin {
    private String name;

    protected User(String name) {
        this.name = name;
    }
}
