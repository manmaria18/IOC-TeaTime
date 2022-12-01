package com.example.iocteatime.repository;

import com.example.iocteatime.domain.Event;

import java.util.List;

public interface IRepositoryEvents {
    Event getEvent();

    List<Event> getAllEvents();

    List<Event> getAllEventsOfAGivenUser(String username);

    void addEvent(Event event);

    void updateEvent(Event event);


}
