package com.example.iocteatime.service;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;
import com.example.iocteatime.repository.RepositoryEvents;

import java.sql.SQLException;
import java.time.LocalDate;
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
    public List<Event> getEventsByDate(String date) {
       return repoEvents.getEventsByDate(date);
    }

    @Override
    public void addEvent(int id, String name, String description, String location, LocalDate date,String startTime,String endTime, String imgURL, List<String> guests, int maxNumberOfAttenders, String eventType, String admin) {
        Event newEvent = new Event(id,name,description,location,date,startTime,endTime,imgURL,guests,maxNumberOfAttenders,eventType,admin);
        repoEvents.addEvent(newEvent);
    }

    @Override
    public void updateEvent(int id, String name, String description, String location, LocalDate date,String startTime,String endTime, String imgURL, List<String> guests, int maxNumberOfAttenders, String eventType, String admin)  {
        Event toUpdateEvent = new Event(id,name,description,location,date,startTime,endTime,imgURL,guests,maxNumberOfAttenders,eventType,admin);
        repoEvents.updateEvent(toUpdateEvent);
    }

    @Override
    public void deleteEvent(int id) {
        repoEvents.deleteEvent(id);
    }

    @Override
    public void deleteEventsByPeriod() {
        List<Event> listOfEvents= repoEvents.getEventsByPeriod();
        for(Event event:listOfEvents){
            repoEvents.deleteEvent(event.getId());
        }
    }




}
