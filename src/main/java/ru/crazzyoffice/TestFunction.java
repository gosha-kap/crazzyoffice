package ru.crazzyoffice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;
import java.util.stream.Stream;

public class TestFunction {
    public static void main(String[] args) {


        Stream.of(120, 410, 85, 32, 314, 12)
                .limit(2)
                .limit(5)
                .forEach(System.out::println);
    }
}