package com.example.iocteatime.controller;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;
import com.example.iocteatime.service.ServiceEvents;
import com.example.iocteatime.service.ServiceGuests;
import com.example.iocteatime.service.ServiceUsers;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MainController {
    private ServiceEvents serviceEvents;
    private ServiceUsers serviceUsers;

    private ServiceGuests serviceGuests;


    public MainController(ServiceEvents serviceEvents, ServiceUsers serviceUsers, ServiceGuests serviceGuests) {
        this.serviceEvents = serviceEvents;
        this.serviceUsers = serviceUsers;
        this.serviceGuests= serviceGuests;
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

   public void joinEvent(int eventId, String username,String enteredBy){

        serviceGuests.joinEvent(eventId,username,enteredBy);
    }

   public void leaveEvent(int eventId, String username){
       serviceGuests.leaveEvent(eventId,username);
    }
   public List<Event> getAllEventsOfAGivenUser(String username){
        return serviceEvents.getAllEventsOfAGivenUser(username);
    }

    public void addEvent(int id, String name, String description, String location, LocalDate date, String startTime, String endTime, String imgURL, List<String> guests, int maxNumberOfAttenders, String eventType, String admin){
        serviceEvents.addEvent(id,name,description,location,date,startTime,endTime,imgURL,guests,maxNumberOfAttenders,eventType,admin);
    }

    public void updateEvent(int id, String name, String description, String location, LocalDate date,String startTime, String endTime, String imgURL, List<String> guests, int maxNumberOfAttenders, String eventType, String admin){
        serviceEvents.updateEvent(id,name,description,location,date,startTime,endTime,imgURL,guests,maxNumberOfAttenders,eventType,admin);
    }

    public void Initialize() {

    }

    public List<Event> getAllEventsWhereYouAreNotAdded(String username){
       return serviceEvents.getAllEventsWhereYouAreNotAdded(username);
    }
    public List<Event> getAllEventsOfOwner(String username) {
       return serviceEvents.getAllEventsOfOwner(username);
    }

    public void clearEventsByPeriod(){
       serviceEvents.deleteEventsByPeriod();
    }

    public List<Event> getEventsByDate(String text) {
       return serviceEvents.getEventsByDate(text);
    }

    public void updateUser(User user,LocalDate lastLogIn) {
       //LocalDate lastLogIn = LocalDate.now();
       serviceUsers.updateUser(user,lastLogIn);
    }

    public List<String> getEventsNotifications(String username){
       return serviceGuests.getEventsNotifications(username);
    }

    public Alert createNotifications(List<String> notifications){
       String notify="";
       for(String notif: notifications){

           Event event = serviceEvents.getEventById(Integer.valueOf(notif.split(" ")[0]));
           notify+=event.getAdmin().toUpperCase();
           notify+=" added you to: ";
           notify+=event.getName();
           notify+="\n";
       }
       Alert alert = new Alert(Alert.AlertType.INFORMATION,notify, ButtonType.OK);
       alert.setTitle("Notifications");
       alert.setHeaderText("NEW PRIVATE EVENTS!");
       return alert;
    }
}
