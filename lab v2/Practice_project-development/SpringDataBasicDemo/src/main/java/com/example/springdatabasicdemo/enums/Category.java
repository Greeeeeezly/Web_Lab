package com.example.springdatabasicdemo.enums;

public enum Category {
    Car(0),
    Buss(1),
    Truck(2),
    Motorcycle(3);

    private int num;
    Category(int i) {
        this.num = i;
    }

    public int getNum() {
        return num;
    }
}
