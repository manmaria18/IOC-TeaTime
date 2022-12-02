package com.example.iocteatime.service;

import com.example.iocteatime.domain.User;
import com.example.iocteatime.repository.RepositoryUser;

import java.util.List;
import java.util.Properties;

public class ServiceUsers implements IServiceUsers{

    private RepositoryUser repoUser;
    public ServiceUsers(RepositoryUser repoUserNou){
        this.repoUser=repoUserNou;
    }
    @Override
    public User getUser(String username) {
        return repoUser.getUser(username);
    }

    @Override
    public List<User> getUsers() {
        return repoUser.getAllUsers();
    }

    @Override
    public void addUser(String username,String password,List<Integer> events) {
       User newUser = new User(username,password,events);
       repoUser.addUser(newUser);
    }

    @Override
    public void login() {

    }
}
