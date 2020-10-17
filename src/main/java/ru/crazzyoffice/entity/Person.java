package ru.crazzyoffice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;

    private  String alias;

    private  String firstName;

    private String lastName;

    private String phone;

    private  DEPARTMENT department;

    @OneToOne(cascade ={CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},
            mappedBy = "person")
    private TelegramUser telegramUser;

    public Person() {
    }

    public Person(Integer id, String alias, DEPARTMENT depart ) {
        this.id = id;
        this.alias = alias;
        this.department = depart;
    }

    public Person(String alias, DEPARTMENT depart) {
        this.alias = alias;
    }

    public Person(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public DEPARTMENT getDepartment() {
        return department;
    }

    public void setDepartment(DEPARTMENT department) {
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", department=" + department +
                '}';
    }
}
