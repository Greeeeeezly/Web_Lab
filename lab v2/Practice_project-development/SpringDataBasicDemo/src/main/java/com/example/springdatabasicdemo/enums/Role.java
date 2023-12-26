package com.example.springdatabasicdemo.enums;

public enum Role {
    User(0),
    Admin(1);

    private int num;
    Role(int i) {
        this.num = i;
    }

    public int getNum() {
        return num;
    }
}
