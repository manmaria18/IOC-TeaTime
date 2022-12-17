package com.example.iocteatime.repository;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryEvents implements IRepositoryEvents{

    private JdbcUtils jdbcUtils;

    public RepositoryEvents(Properties props){

        this.jdbcUtils = new JdbcUtils(props);
    }


    @Override
    public List<Event> getEventsByName(String name) throws SQLException {
        //Event event = null;
        List<Event> events = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        List<String> guests  = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from Events where name='"+name+"'")) {
            try (ResultSet rows = ps.executeQuery()) {
                while (rows.next()) {
                    //int id = rows.getInt("id");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String time = rows.getString("dateTime");
                    //LocalDateTime dateTime = LocalDateTime.parse(rows.getString("dateTime"),formatter);
                    String[] splittedDateTime= time.split(" ");
                    String dateAsString = splittedDateTime[0];
                    LocalDate date = LocalDate.parse(dateAsString,formatter);
                    String startTime = splittedDateTime[1];
                    String endTime = splittedDateTime[2];
                    Event event1 = new Event(rows.getInt("id"), rows.getString("name"),
                            rows.getString("description"), rows.getString("location"),date,startTime,endTime, rows.getString("imgURL"), guests,rows.getInt("maxNumberOfAttenders"),rows.getString("eventType"),rows.getString("admin"));
                    //employees.add(employee);
                    events.add(event1);
                    //}
                }
            }
        }catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
        for(Event event : events){
            try(PreparedStatement ps =con.prepareStatement("select username from Guests where id='"+event.getId()+"'")){
                try(ResultSet rows = ps.executeQuery()){
                    // int i=0;
                    while(rows.next()){
                        // eventList.get(i) = rows.getInt("id");
                        String username = rows.getString("username");
                        guests.add(username);
                    }
                }
            }catch (SQLException ex){
                System.err.println("Error DB"+ex);
            }
            event.setGuests(guests);
        }
        return events;
    }

    @Override
    public List<Event> getAllEvents() {
        //
        /*List<Event> listOfEvents = getEventsByPeriod();
        for(Event event:listOfEvents){
            deleteEvent(event.getId());
        }*/

        //
        List<Event> events = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        List<String> guests  = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from Events")) {
            try (ResultSet rows = ps.executeQuery()) {
                while (rows.next()) {
                    int id = rows.getInt("id");

                    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    //LocalDateTime dateTime = LocalDateTime.parse(time,formatter);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String time = rows.getString("dateTime");
                    //LocalDateTime dateTime = LocalDateTime.parse(rows.getString("dateTime"),formatter);
                    String[] splittedDateTime= time.split(" ");
                    String dateAsString = splittedDateTime[0];
                    LocalDate date = LocalDate.parse(dateAsString,formatter);
                    String startTime = splittedDateTime[1];
                    String endTime = splittedDateTime[2];
                    Event event1 = new Event(rows.getInt("id"), rows.getString("name"),
                            rows.getString("description"),rows.getString("location"),date,startTime,endTime, rows.getString("imgURL"), guests,rows.getInt("maxNumberOfAttenders"),rows.getString("eventType"),rows.getString("admin"));
                    //employees.add(employee);
                    events.add(event1);
                    //}
                }
            }
        }catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
        for(Event event : events){
            try(PreparedStatement ps =con.prepareStatement("select username from Guests where id='"+event.getId()+"'")){
                try(ResultSet rows = ps.executeQuery()){
                    // int i=0;
                    while(rows.next()){
                        // eventList.get(i) = rows.getInt("id");
                        String username = rows.getString("username");
                        guests.add(username);
                    }
                }
            }catch (SQLException ex){
                System.err.println("Error DB"+ex);
            }
            event.setGuests(guests);
        }
        return events;
    }

    @Override
    public List<Event> getAllEventsOfAGivenUser(String username) {
        List<Event> events = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        List<Integer> eventsList= new ArrayList<>();
        List<String> guests = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement("select id from Guests where username='"+username+"'")){
            try(ResultSet rows = ps.executeQuery()){
                // int i=0;
                while(rows.next()){
                    // eventList.get(i) = rows.getInt("id");
                    int id = rows.getInt("id");
                    eventsList.add(id);
                }
            }
        }catch (SQLException ex){
            System.err.println("Error DB"+ex);
        }
        for(int eventId : eventsList){
            try(PreparedStatement ps = con.prepareStatement("select * from Events where id='"+eventId+"'")) {
                try (ResultSet rows = ps.executeQuery()) {
                    while (rows.next()) {
                        int id = rows.getInt("id");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(rows.getString("dateTime"),formatter);
                        String[] splittedDateTime= dateTime.toString().split(" ");
                        String dateAsString = splittedDateTime[0];
                        LocalDate date = LocalDate.parse(dateAsString);
                        String startTime = splittedDateTime[1];
                        String endTime = splittedDateTime[2];
                        Event event1 = new Event(rows.getInt("id"), rows.getString("name"),
                                rows.getString("description"), rows.getString("location"),date, startTime,endTime, rows.getString("imgURL"), guests,rows.getInt("maxNumberOfAttenders"),rows.getString("eventType"),rows.getString("admin"));
                        //employees.add(employee);
                        events.add(event1);
                        //}
                    }
                }
            }catch (SQLException ex) {

                System.err.println("Error DB"+ex);
            }

        }
        for(Event event : events){
            try(PreparedStatement ps =con.prepareStatement("select username from Guests where id='"+event.getId()+"'")){
                try(ResultSet rows = ps.executeQuery()){
                    // int i=0;
                    while(rows.next()){
                        // eventList.get(i) = rows.getInt("id");
                        String username1 = rows.getString("username");
                        guests.add(username1);
                    }
                }
            }catch (SQLException ex){
                System.err.println("Error DB"+ex);
            }
            event.setGuests(guests);
        }
        return events;
    }

    @Override
    public void addEvent(Event event) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("insert into Events" +
                "(name,description,location,dateTime,imgURL,maxNumberOfAttenders,eventType,admin) values (?,?,?,?,?,?,?,?)")){
            ps.setString(1, event.getName());
            ps.setString(2,event.getDescription());
            ps.setString(3,event.getLocation());
            ps.setString(4, event.getDate().toString().replace("T"," ") + " " + event.getStartTime() + " " + event.getEndTime());
            ps.setString(5,event.getImgURL());
            ps.setInt(6,event.getMaxNumberOfAttenders());
            ps.setString(7,event.getEventType());
            ps.setString(8,event.getAdmin());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public void updateEvent(Event event) {

        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("update Events set name='" +event.getName() +
                "', description='" +event.getDescription()+
                "', location='" +event.getLocation()+"', dateTime='"+event.getDate()+ " " +
                 event.getStartTime() + " " + event.getEndTime() +"', imgURL='"+event.getImgURL()+
                "', maxNumberOfAttenders='"+event.getMaxNumberOfAttenders()+ " " +
                "', eventType='"+event.getEventType()+ " " +
                "', admin='"+event.getAdmin()+ " " +
                "'where id='"+event.getId()+"'")){
                ps.executeUpdate();
        } catch (SQLException ex) {

                System.err.println("Error DB"+ex);
        }

    }

    @Override
    public void deleteEvent(int id) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("DELETE FROM Events where id='"+id+"'")){
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public Event getEventById(int id) {
        Connection con = jdbcUtils.getConnection();
        List<String> guests = new ArrayList<>();
        Event event = new Event();
        try (PreparedStatement ps = con.prepareStatement("select * from Events where id='" + id + "'")) {
            try (ResultSet rows = ps.executeQuery()) {
                if (rows.next()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String time = rows.getString("dateTime");
                    String[] splittedDateTime = time.split(" ");
                    String dateAsString = splittedDateTime[0];
                    LocalDate date = LocalDate.parse(dateAsString, formatter);
                    String startTime = splittedDateTime[1];
                    String endTime = splittedDateTime[2];
                    event = new Event(id, rows.getString("name"),
                            rows.getString("description"), rows.getString("location"), date, startTime, endTime, rows.getString("imgURL"), guests, rows.getInt("maxNumberOfAttenders"), rows.getString("eventType"), rows.getString("admin"));
                }
            }
        } catch (SQLException ex) {

            System.err.println("Error DB" + ex);
        }
        try (PreparedStatement ps = con.prepareStatement("select username from Guests where id='" + event.getId() + "'")) {
            try (ResultSet rows = ps.executeQuery()) {
                while (rows.next()) {
                    String username = rows.getString("username");
                    guests.add(username);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB" + ex);
        }
        event.setGuests(guests);
        return event;
    }

    @Override
    public List<Event> getEventsByPeriod() {
        List<Integer> listOfEvents = new ArrayList<>();
        List<Integer> listOfGuests = new ArrayList<>();


        List<Event> events = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        List<String> guests  = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from Events")) {
            try (ResultSet rows = ps.executeQuery()) {
                while (rows.next()) {
                    Integer id = rows.getInt("id");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String time = rows.getString("dateTime");
                    String[] splittedDateTime= time.split(" ");
                    String dateAsString = splittedDateTime[0];
                    LocalDate date = LocalDate.parse(dateAsString,formatter);
                    LocalDate dateNow=LocalDate.now();
                    Integer daysBetween= dateNow.compareTo(date);
                    if(daysBetween>7){
                        listOfEvents.add(id);
                    }
                }
            }
        }catch (SQLException ex) {
            System.err.println("Error DB"+ex);
        }

        try(PreparedStatement ps = con.prepareStatement("select id from Guests")) {
            try (ResultSet rows = ps.executeQuery()) {
                while (rows.next()) {
                    Integer id = rows.getInt("id");
                    listOfGuests.add(id);
                }
            }
        }catch (SQLException ex) {
            System.err.println("Error DB"+ex);
        }

        List<Integer> listOfEventsWithoutGuests= new ArrayList<>();
        for(Integer id: listOfEvents){
            if(listOfGuests.contains(id)){
                break;
            }else{
                listOfEventsWithoutGuests.add(id);
            }
        }
        for (Integer id :listOfEventsWithoutGuests){
            events.add(getEventById(id));
        }
        return events;
    }

    @Override
    public void joinEvent(Event event, User user) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("insert into Guests" +
                "(username,id) values (?,?)")){
            ps.setString(1, user.getUsername());
            ps.setInt(2,event.getId());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public void leaveEvent(Event event, User user) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("DELETE FROM Guests where username='"+user.getUsername()+"' AND id='"+event.getId()+"'")){
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public List<Event> getEventsByDate(String date) {

        List<Event> events = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        List<String> guests  = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from Events where dateTime='"+date+"'")) {
            try (ResultSet rows = ps.executeQuery()) {
                while (rows.next()) {
                    //int id = rows.getInt("id");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String time = rows.getString("dateTime");
                    //LocalDateTime dateTime = LocalDateTime.parse(rows.getString("dateTime"),formatter);
                    String[] splittedDateTime= time.split(" ");
                    String dateAsString = splittedDateTime[0];
                    LocalDate date1 = LocalDate.parse(dateAsString,formatter);
                    String startTime = splittedDateTime[1];
                    String endTime = splittedDateTime[2];
                    Event event1 = new Event(rows.getInt("id"), rows.getString("name"),
                            rows.getString("description"), rows.getString("location"),date1,startTime,endTime, rows.getString("imgURL"), guests,rows.getInt("maxNumberOfAttenders"),rows.getString("eventType"),rows.getString("admin"));
                    //employees.add(employee);
                    events.add(event1);
                    //}
                }
            }
        }catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
        for(Event event : events){
            try(PreparedStatement ps =con.prepareStatement("select username from Guests where id='"+event.getId()+"'")){
                try(ResultSet rows = ps.executeQuery()){
                    // int i=0;
                    while(rows.next()){
                        // eventList.get(i) = rows.getInt("id");
                        String username = rows.getString("username");
                        guests.add(username);
                    }
                }
            }catch (SQLException ex){
                System.err.println("Error DB"+ex);
            }
            event.setGuests(guests);
        }
        return events;
    }
}
