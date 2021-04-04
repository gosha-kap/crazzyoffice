package ru.crazzyoffice.entity;

public enum  DEPARTMENT {
    Неизвестно("000"),
    Отдел_В("001"),
    Отдел_Р("002"),
    Отдел_К("003"),
    Отдел_И("004"),
    Аналитика("005"),
    Цкп("006"),
    Кадры("007"),
    ССК("008"),
    Руководители("009"),
    Секретари("010"),
    Гараж("011"),
    Уссурийск("012"),
    Находка("013"),
    Арсеньев("014"),
    Спасск_Дальний("015");



    private String code;

    DEPARTMENT(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
