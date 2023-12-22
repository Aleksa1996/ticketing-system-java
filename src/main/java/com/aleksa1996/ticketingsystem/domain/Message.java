package com.aleksa1996.ticketingsystem.domain;

import java.util.Date;

public class Message implements Comparable<Message> {

    private int id;

    private String user;

    private String content;

    private Date wroteOn;

    public Message() {
        super();
    }

    public Message(String user, String content) {

        setUser(user);
        setContent(content);
        setWroteOn(new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    private void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public Date getWroteOn() {
        return wroteOn;
    }

    private void setWroteOn(Date wroteOn) {
        this.wroteOn = wroteOn;
    }

    @Override
    public int compareTo(Message message) {
        return message.getWroteOn().compareTo(wroteOn);
    }
}
