package com.example.iocteatime.repository;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryUser implements IRepositoryUser {

    private JdbcUtils jdbcUtils;

    public RepositoryUser(Properties props){

        this.jdbcUtils = new JdbcUtils(props);
    }

    @Override
    public User getUser(String username) {
        Connection con = jdbcUtils.getConnection();
        User user = new User();
        List<Integer> eventList = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement("select * from Users where username='"+username+"'")){
            try(ResultSet rows = ps.executeQuery()){
                //while(rows.next()){
               // int id = rows.getInt("id");
                User user1 = new User(rows.getString("username"),
                        rows.getString("password"), eventList);
                //employees.add(employee);
                user=user1;
                //}
            }
        } catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }

        try(PreparedStatement ps =con.prepareStatement("select id from Guests where username='"+username+"'")){
            try(ResultSet rows = ps.executeQuery()){
               // int i=0;
                while(rows.next()){
                   // eventList.get(i) = rows.getInt("id");
                   int id = rows.getInt("id");
                   eventList.add(id);
                }
            }
        }catch (SQLException ex){
            System.err.println("Error DB"+ex);
        }
        user.setEvents(eventList);

        return user;

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection con = jdbcUtils.getConnection();
        //User user = null;
        List<Integer> eventList = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement("select * from Users")) {
            try (ResultSet rows = ps.executeQuery()) {
                while (rows.next()) {
                    int id = rows.getInt("id");
                    User user1 = new User(rows.getString("username"),
                            rows.getString("password"), eventList);
                    users.add(user1);
                    //user=user1;
                    //}
                }
            }

        } catch (SQLException ex) {

            System.err.println("Error DB" + ex);
        }
        for(User user: users){
            try(PreparedStatement ps =con.prepareStatement("select id from Guests where username='"+user.getUsername()+"'")){
                try(ResultSet rows = ps.executeQuery()){
                    // int i=0;
                    while(rows.next()){
                        // eventList.get(i) = rows.getInt("id");
                        int id = rows.getInt("id");
                        eventList.add(id);
                    }
                }
            }catch (SQLException ex){
                System.err.println("Error DB"+ex);
            }
            user.setEvents(eventList);
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("insert into Users" +
                "(username,password) values (?,?)")){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            //ps.setString(3,employee.getStatus());
            int result = ps.executeUpdate();
        } catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public void logIn(User user) {

    }

    @Override
    public void updateUser(User user, LocalDate lastLogIn) {
        Connection con = jdbcUtils.getConnection();
        try(PreparedStatement ps = con.prepareStatement("update Users set lastLogIn='" +lastLogIn + "'where username='"+user.getUsername()+"'")){
            ps.executeUpdate();
        } catch (SQLException ex) {

            System.err.println("Error DB"+ex);
        }
    }
}
