package com.example.demo.car.user;

public class UserRequest {

    private String name;

    private String address;
    private String phoneNumber;

    private String businessNumber;

    private String department;

    private Type type;

    enum Type {
        CUSTOMER, STORE, ADMIN
    }

    public User form(UserRequest before) {
        return switch (before.type) {
            case CUSTOMER -> new Customer(before.name, before.address, before.phoneNumber);
            case STORE -> new Store(before.name, before.address, before.businessNumber);
            case ADMIN -> new Admin(before.name, before.department);
        };
    }
}
