package com.example.iocteatime.controller;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;
import com.example.iocteatime.service.ServiceEvents;
import com.example.iocteatime.service.ServiceUsers;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MainController {
    private ServiceEvents serviceEvents;
    private ServiceUsers serviceUsers;

    public MainController(ServiceEvents serviceEvents, ServiceUsers serviceUsers) {
        this.serviceEvents = serviceEvents;
        this.serviceUsers = serviceUsers;
    }

    /*     Calls to ServiceUser   */
    User getUser(String username){
        return serviceUsers.getUser(username);
    }

    List<User> getUsers(){
        return serviceUsers.getUsers();
    }

   public void addUser(String username,String password,List<Integer> events){
        serviceUsers.addUser(username,password,events);

    }


    /*     Calls to ServiceEvents   */

    List<Event> getEventsByName(String name) throws SQLException {
        return serviceEvents.getEventsByName(name);
    }

    List<Event> getAllEvents(){
        return serviceEvents.getAllEvents();
    }

    List<Event> getAllEventsOfAGivenUser(String username){
        return serviceEvents.getAllEventsOfAGivenUser(username);
    }

    public void addEvent(int id, String name, String description, String location, LocalDateTime dateTime, String imgURL, List<String> guests){
        serviceEvents.addEvent(id,name,description,location,dateTime,imgURL,guests);
    }

    public void updateEvent(int id, String name, String description, String location, LocalDateTime dateTime, String imgURL, List<String> guests){
        serviceEvents.updateEvent(id,name,description,location,dateTime,imgURL,guests);
    }

}
