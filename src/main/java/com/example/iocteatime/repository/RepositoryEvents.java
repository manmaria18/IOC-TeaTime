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
        String eventType = "Pub";
        try(PreparedStatement ps = con.prepareStatement("select * from Events where eventType='"+eventType+"'")) {
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
                            rows.getString("description"),rows.getString("location"),date,startTime,endTime, rows.getString("imgURL"), new ArrayList<String>(),rows.getInt("maxNumberOfAttenders"),rows.getString("eventType"),rows.getString("admin"));
                    //employees.add(employee);
                    events.add(event1);
                    //}
                }
            }
        }catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
        for(Event event : events){
            List<String> guests  = new ArrayList<>();
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
    public List<Event> getAllEventsPrivate() {
        List<Event> events = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        String eventType = "Private";
        try(PreparedStatement ps = con.prepareStatement("select * from Events where eventType='"+eventType+"'")) {
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
                            rows.getString("description"),rows.getString("location"),date,startTime,endTime, rows.getString("imgURL"), new ArrayList<String>(),rows.getInt("maxNumberOfAttenders"),rows.getString("eventType"),rows.getString("admin"));
                    //employees.add(employee);
                    events.add(event1);
                    //}
                }
            }
        }catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
        for(Event event : events){
            List<String> guests  = new ArrayList<>();
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
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        //LocalDateTime dateTime = LocalDateTime.parse(rows.getString("dateTime"),formatter);
                        String dateTime = rows.getString("dateTime");
                        String[] splittedDateTime= dateTime.split(" ");
                        String dateAsString = splittedDateTime[0];
                        LocalDate date = LocalDate.parse(dateAsString,formatter);
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
            System.out.println("1:\n"+guests);
            System.out.println("2:\n"+event.getGuests());
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
        if(event.getGuests().size()!=0){
            for(String guest : event.getGuests()){
                User user = new User();
                List<Event> current= getAllEventsOfAllKind();
                Event lastAdded = current.get(current.size()-1);
                event.setId(lastAdded.getId());
                user.setUsername(guest);
                String enteredBy = "add";
                joinEvent(event,user,enteredBy);
            }
        }
    }

    private List<Event> getAllEventsOfAllKind() {
        //
        /*List<Event> listOfEvents = getEventsByPeriod();
        for(Event event:listOfEvents){
            deleteEvent(event.getId());
        }*/

        //
        List<Event> events = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        List<String> guests  = new ArrayList<>();
        String eventType = "Pub";
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
    public void updateEvent(Event event) {

        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("update Events set name='" +event.getName() +
                "', description='" +event.getDescription()+
                "', location='" +event.getLocation()+"', dateTime='"+event.getDate()+ " " +
                 event.getStartTime() + " " + event.getEndTime() +"', imgURL='"+event.getImgURL()+
                "', maxNumberOfAttenders='"+event.getMaxNumberOfAttenders()+
                "', eventType='"+event.getEventType()+
                "', admin='"+event.getAdmin() +
                "' where id='"+event.getId()+"'")){
                ps.executeUpdate();
        } catch (SQLException ex) {

                System.err.println("Error DB"+ex);
        }
        if(event.getGuests().size()!=0){
            for(String guest : event.getGuests()){
                User user = new User();
                user.setUsername(guest);
                String enteredBy = "add";
                joinEvent(event,user,enteredBy);
            }
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
    public void joinEvent(Event event, User user,String enteredBy) {
        Connection con = jdbcUtils.getConnection();
        LocalDate enteredDate = LocalDate.now();
        try(PreparedStatement ps = con.prepareStatement("insert into Guests" +
                "(username,id,enteredBy,enteredDate) values (?,?,?,?)")){
            ps.setString(1, user.getUsername());
            ps.setInt(2,event.getId());
            ps.setString(3,enteredBy);
            ps.setString(4,enteredDate.toString());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
        //user.getEvents(user.getUsername()).add(event.getId());
        List<Event> eventsOfCurrentUser= getAllEventsOfAGivenUser(user.getUsername());
        List<Integer> eventsIdsOfCurrentUser = new ArrayList<>();
        for(Event event1: eventsOfCurrentUser){
            eventsIdsOfCurrentUser.add(event1.getId());
        }
        user.setEvents(eventsIdsOfCurrentUser);

    }

    @Override
    public void leaveEvent(int eventId, String username) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("DELETE FROM Guests where username='"+username+"' AND id='"+eventId+"'")){
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public List<String> getEventsNotifications(User user) {
        Connection con = jdbcUtils.getConnection();
        List<String> guests = new ArrayList<>();
        List<String> goodOnes = new ArrayList<>();
        String enteredBy = "add";
        try(PreparedStatement ps =con.prepareStatement("select * from Guests where enteredBy='"+enteredBy+"'and username='"+user.getUsername()+"'")){
            try(ResultSet rows = ps.executeQuery()){
                // int i=0;
                while(rows.next()){
                    // eventList.get(i) = rows.getInt("id");
                    int id = rows.getInt("id");
                    String date = rows.getString("enteredDate");
                    guests.add(id+" "+date);
                }
            }
        }catch (SQLException ex){
            System.err.println("Error DB"+ex);
        }
        //event.setGuests(guests);
        for(String guest : guests){
            System.out.println(guest);
            String date = guest.split(" ")[1];
            System.out.println(date);
            //LocalDate good = new Date(date,formatter);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate good = LocalDate.parse(date,formatter);
            System.out.println(user.getLastLogIn());
            if(good.compareTo(user.getLastLogIn())<=0){
                goodOnes.add(guest);
            }
        }
        return goodOnes;
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
