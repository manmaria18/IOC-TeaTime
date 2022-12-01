package com.example.iocteatime.domain;

import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Event> events;

    public User(String username, String password, List<Event> events) {
        this.username = username;
        this.password = password;
        this.events = events;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getEvents(String username) {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", events=" + events +
                '}';
    }
}
