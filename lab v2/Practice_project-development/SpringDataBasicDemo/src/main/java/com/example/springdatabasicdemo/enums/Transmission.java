package com.example.springdatabasicdemo.enums;

public enum Transmission {
    MANUAL(0),
    AUTOMATIC(1);

    private int num;
    Transmission(int i) {
        this.num = i;
    }

    public int getNum() {
        return num;
    }
}
