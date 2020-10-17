package ru.crazzyoffice.entity;

public enum  DEPARTMENT {
    VSK("v"),
    IZBA("i");

    private String code;

    DEPARTMENT(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
