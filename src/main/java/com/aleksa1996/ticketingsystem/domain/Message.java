package com.aleksa1996.ticketingsystem.domain;

public class Message extends Entity {

    private User user;

    private String content;

    public Message(Id id, User user, String content) {
        super(id);

        setUser(user);
        setContent(content);
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }
}
