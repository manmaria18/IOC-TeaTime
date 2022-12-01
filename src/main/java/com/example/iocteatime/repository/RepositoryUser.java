package com.example.iocteatime.repository;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        User user = null;
        List<Integer> eventList = null;
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
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void logIn(User user) {

    }
}
