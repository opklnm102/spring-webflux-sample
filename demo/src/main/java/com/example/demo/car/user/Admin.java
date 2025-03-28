package com.example.demo.car.user;

public final class Admin extends User {
    private String department;

    public Admin(String name, String department) {
        super(name);
        this.department = department;
    }
}
