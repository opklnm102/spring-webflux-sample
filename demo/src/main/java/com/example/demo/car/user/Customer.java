package com.example.demo.car.user;

public final class Customer extends User {

    private String address;
    private String phoneNumber;

    public Customer(String name, String address, String phoneNumber) {
        super(name);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
