package com.aleksa1996.ticketingsystem.domain;

public class Message {

    private int id;

    private String user;

    private String content;

    public Message(String user, String content) {

        setUser(user);
        setContent(content);
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

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!this.getClass().equals(obj.getClass()))
            return false;

        Message obj2 = (Message) obj;
        if ((this.id == obj2.getId()) && (this.user.equals(obj2.getUser()))
                && (this.content.equals(obj2.getContent()))) {

            return true;
        }

        return false;
    }

    public int hashCode() {
        int tmp = 0;

        tmp = (id + user + content).hashCode();

        return tmp;
    }
}
