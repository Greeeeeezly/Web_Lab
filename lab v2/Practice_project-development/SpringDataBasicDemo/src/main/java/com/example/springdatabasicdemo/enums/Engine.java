package com.example.springdatabasicdemo.enums;

public enum Engine {
    GASOLINE(0),
    DIESEL(1),
    ELECTRIC(2),
    HYBRID(3);
    private int num;

    Engine(int i) {
        this.num = i;
    }

    public int getNum() {
        return num;
    }

}
