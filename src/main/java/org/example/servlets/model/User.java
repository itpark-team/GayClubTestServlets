package org.example.servlets.model;

public class User {
    private int id;
    private String nickname;
    private int ticketPrice;

    public User(int id, String nickname, int ticketPrice) {
        this.id = id;
        this.nickname = nickname;
        this.ticketPrice = ticketPrice;
    }

    public void setId(int id) {
        this.id = id;
    }
}
