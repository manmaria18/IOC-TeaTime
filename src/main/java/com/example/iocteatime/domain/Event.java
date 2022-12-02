package com.example.iocteatime.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private String imgURL;

    private List<String> guests;

    public Event(int id, String name, String description, String location, LocalDateTime dateTime, String imgURL, List<String> guests) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.imgURL = imgURL;
        this.guests = guests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public List<String> getGuests() {
        return guests;
    }

    public void setGuests(List<String> guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", date='" + dateTime + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", guests=" + guests +
                '}';
    }
}
