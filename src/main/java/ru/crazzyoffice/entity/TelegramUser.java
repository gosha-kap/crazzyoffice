package ru.crazzyoffice.entity;

import javax.persistence.*;

@Entity
public class TelegramUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String chatId;

    private Boolean autorised;

    @OneToOne(cascade ={CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public TelegramUser() {
    }

    public TelegramUser(Integer userId, Person person ,Boolean auth) {
         this.userId = userId;
         this.person =person;
         this.autorised = auth;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getAutorised() {
        return autorised;
    }

    public void setAutorised(Boolean autorised) {
        this.autorised = autorised;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
