package com.example.demo.car.user;

public final class Store extends User{
    private String address;
    private String businessNumber;

    public Store(String name, String address, String businessNumber) {
        super(name);
        this.address = address;
        this.businessNumber = businessNumber;
    }
}
