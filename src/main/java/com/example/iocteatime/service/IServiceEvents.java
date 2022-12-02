package com.example.iocteatime.service;

import com.example.iocteatime.domain.Event;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface IServiceEvents {
    List<Event> getEventsByName(String name) throws SQLException;
    List<Event> getAllEvents();
    List<Event> getAllEventsOfAGivenUser(String username);
    void addEvent(int id, String name, String description, String location, LocalDateTime dateTime, String imgURL, List<String> guests);
    void updateEvent(int id, String name, String description, String location, LocalDateTime dateTime, String imgURL, List<String> guests);

}
