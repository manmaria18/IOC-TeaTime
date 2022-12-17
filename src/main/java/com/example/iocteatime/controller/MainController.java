package com.example.iocteatime.controller;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;
import com.example.iocteatime.service.ServiceEvents;
import com.example.iocteatime.service.ServiceUsers;

import java.sql.SQLException;
import java.time.LocalDate;
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
   public  User getUser(String username){
        return serviceUsers.getUser(username);
    }

   public  List<User> getUsers(){
        return serviceUsers.getUsers();
    }

   public void addUser(String username,String password,List<Integer> events){
        serviceUsers.addUser(username,password,events);

    }


    /*     Calls to ServiceEvents   */

   public List<Event> getEventsByName(String name) throws SQLException {
        return serviceEvents.getEventsByName(name);
    }

   public List<Event> getAllEvents(){
        return serviceEvents.getAllEvents();
    }

   public void joinEvent(Event event,User user){
        serviceEvents.joinEvent(event,user);
    }

   public void leaveEvent(Event event,User user){
       serviceEvents.leaveEvent(event,user);
    }
   public List<Event> getAllEventsOfAGivenUser(String username){
        return serviceEvents.getAllEventsOfAGivenUser(username);
    }

    public void addEvent(int id, String name, String description, String location, LocalDate date,String startTime, String endTime, String imgURL, List<String> guests, int maxNumberOfAttenders, String eventType, String admin){
        serviceEvents.addEvent(id,name,description,location,date,startTime,endTime,imgURL,guests,maxNumberOfAttenders,eventType,admin);
    }

    public void updateEvent(int id, String name, String description, String location, LocalDate date,String startTime, String endTime, String imgURL, List<String> guests, int maxNumberOfAttenders, String eventType, String admin){
        serviceEvents.updateEvent(id,name,description,location,date,startTime,endTime,imgURL,guests,maxNumberOfAttenders,eventType,admin);
    }

    public void Initialize() {

    }

    public void clearEventsByPeriod(){
       serviceEvents.deleteEventsByPeriod();
    }

    public List<Event> getEventsByDate(String text) {
       return serviceEvents.getEventsByDate(text);
    }
}
