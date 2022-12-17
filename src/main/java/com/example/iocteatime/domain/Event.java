package com.example.iocteatime.domain;


import java.time.LocalDate;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String description;
    private String location;
    private LocalDate date;
    private String startTime;
    private String endTime;
    private String imgURL;

    private List<String> guests;

    private int maxNumberOfAttenders;

    private String eventType;

    private String admin;

    public Event(int id, String name, String description, String location, LocalDate date,String startTime,String endTime, String imgURL, List<String> guests, int max, String eventType, String admin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.imgURL = imgURL;
        this.guests = guests;
        this.eventType = eventType;
        this.maxNumberOfAttenders = max;
        this.admin=admin;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public int getMaxNumberOfAttenders() {
        return maxNumberOfAttenders;
    }

    public void setMaxNumberOfAttenders(int maxNumberOfAttenders) {
        this.maxNumberOfAttenders = maxNumberOfAttenders;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", guests=" + guests + '\'' +
                ", eventType="+ eventType + '\'' +
                ", maxNumberOfAttenders="+ maxNumberOfAttenders + '\'' +
                ", admin="+ admin +
                '}';
    }
}
