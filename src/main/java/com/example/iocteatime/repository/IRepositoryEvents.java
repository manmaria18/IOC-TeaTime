package com.example.iocteatime.repository;

import com.example.iocteatime.domain.Event;

import java.sql.SQLException;
import java.util.List;

public interface IRepositoryEvents {
    List<Event> getEventsByName(String name) throws SQLException;

    List<Event> getAllEvents();

    List<Event> getAllEventsOfAGivenUser(String username);

    List<Event> getEventsByDate(String date);

    void addEvent(Event event);

    void updateEvent(Event event);


}
