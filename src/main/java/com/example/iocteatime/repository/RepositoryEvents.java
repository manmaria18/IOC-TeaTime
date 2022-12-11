package com.example.iocteatime.repository;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;

import java.sql.*;
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
                    int id = rows.getInt("id");
                    Event event1 = new Event(rows.getInt("id"), rows.getString("name"),
                            rows.getString("description"), rows.getString("location"), rows.getTimestamp("dateTime").toLocalDateTime(), rows.getString("imgURL"), guests);
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
        List<Event> events = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        List<String> guests  = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from Events")) {
            try (ResultSet rows = ps.executeQuery()) {
                while (rows.next()) {
                    int id = rows.getInt("id");
                    String time = rows.getString("dateTime");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(time,formatter);
                    Event event1 = new Event(rows.getInt("id"), rows.getString("name"),
                            rows.getString("description"), rows.getString("location"),dateTime, rows.getString("imgURL"), guests);
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
                        Event event1 = new Event(rows.getInt("id"), rows.getString("name"),
                                rows.getString("description"), rows.getString("location"), rows.getTimestamp("dateTime").toLocalDateTime(), rows.getString("imgURL"), guests);
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
                "(name,description,location,dateTime,imgURL) values (?,?,?,?,?)")){
            ps.setString(1, event.getName());
            ps.setString(2,event.getDescription());
            ps.setString(3,event.getLocation());
            ps.setString(4, event.getDateTime().toString().replace("T"," "));
            ps.setString(5,event.getImgURL());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public void updateEvent(Event event) {

        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("update Events set name='" +event.getName() + "', description='" +event.getDescription()+ "', location='" +event.getLocation()+"', dateTime='"+event.getDateTime()+"', imgURL='"+event.getImgURL()+"'where id='"+event.getId()+"'")){
                //ps.setString(1, utilizator.getIdUtilizator());
                //ps.setString(2, utilizator.getNume());
                //ps.setString(3, utilizator.getStatus());
                //ps.setString(4, utilizator.getPassword());
                ps.executeUpdate();
        } catch (SQLException ex) {

                System.err.println("Error DB"+ex);
        }

    }
}
