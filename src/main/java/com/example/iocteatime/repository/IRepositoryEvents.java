package com.example.iocteatime.repository;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface IRepositoryEvents {
    List<Event> getEventsByName(String name) throws SQLException;

    List<Event> getAllEvents();
    List<Event> getAllEventsPrivate();

    List<Event> getAllEventsOfAGivenUser(String username);

    List<Event> getEventsByDate(String date);

    void addEvent(Event event);

    void updateEvent(Event event);

    void deleteEvent(int id);

    Event getEventById(int id);

    List<Event> getEventsByPeriod();

    void joinEvent(Event eventId, User username, String enteredBy);

    void leaveEvent(int eventId, String username);

    List<String> getEventsNotifications(User user);
}
