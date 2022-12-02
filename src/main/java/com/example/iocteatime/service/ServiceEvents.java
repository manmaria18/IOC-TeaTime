package com.example.iocteatime.service;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.repository.RepositoryEvents;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceEvents implements IServiceEvents{

    private RepositoryEvents repoEvents;


    public ServiceEvents(RepositoryEvents newRepoEvents){
        this.repoEvents=newRepoEvents;
    }

    @Override
    public List<Event> getEventsByName(String name) throws SQLException {
        return repoEvents.getEventsByName(name);
    }

    @Override
    public List<Event> getAllEvents() {
        return repoEvents.getAllEvents();
    }

    @Override
    public List<Event> getAllEventsOfAGivenUser(String username) {
        return repoEvents.getAllEventsOfAGivenUser(username);
    }

    @Override
    public void addEvent(int id, String name, String description, String location, LocalDateTime dateTime, String imgURL, List<String> guests) {
        Event newEvent = new Event(id,name,description,location,dateTime,imgURL,guests);
        repoEvents.addEvent(newEvent);
    }

    @Override
    public void updateEvent(int id, String name, String description, String location, LocalDateTime dateTime, String imgURL, List<String> guests) {
        Event toUpdateEvent = new Event(id,name,description,location,dateTime,imgURL,guests);
        repoEvents.updateEvent(toUpdateEvent);
    }
}
