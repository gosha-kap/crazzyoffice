package ru.crazzyoffice.entity;

import javax.persistence.*;

@Entity
public class TelegramUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long userId;

    private Long  chatId;

    private Boolean autorised;

    private String first;

    private String last;

    public TelegramUser() {
    }



    public TelegramUser(Long userId , Long chatId, Boolean auth, String first, String last) {
         this.userId = userId;
         this.autorised = auth;
         this.first = first;
         this.last = last;
         this.chatId = chatId;

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getAutorised() {
        return autorised;
    }

    public void setAutorised(Boolean autorised) {
        this.autorised = autorised;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
