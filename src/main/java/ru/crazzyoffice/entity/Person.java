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


    public Person() {
    }

    public Person(Integer id, String alias) {
        this.id = id;
        this.alias = alias;
    }

    public Person(String alias) {
        this.alias = alias;
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


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                '}';
    }
}
