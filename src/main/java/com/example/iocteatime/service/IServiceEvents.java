package com.example.iocteatime.service;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IServiceEvents {
    Event getEventById(int id);
    List<Event> getEventsByName(String name) throws SQLException;
    List<Event> getAllEvents();
    List<Event> getAllEventsOfOwner(String username);
    List<Event> getAllEventsWhereYouAreNotAdded(String username);
    List<Event> getAllEventsOfAGivenUser(String username);
    List<Event> getEventsByDate(String date);
    void addEvent(int id, String name, String description, String location, LocalDate date,String startTime,String endTime, String imgURL, List<String> guests,int maxNumberOfGuests, String eventType, String admin) ;
    void updateEvent(int id, String name, String description, String location, LocalDate date,String startTime,String endTime, String imgURL, List<String> guests,int maxNumberOfGuests, String eventType, String admin) ;
    void deleteEvent(int id) ;
    void deleteEventsByPeriod();


}
