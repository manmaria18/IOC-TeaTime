package com.example.iocteatime.service;

import com.example.iocteatime.domain.User;

import java.util.List;

public interface IServiceUsers {
    User getUser(String username);

    List<User> getUsers();

    void addUser(String username,String password,List<Integer> events);

    void login();


}
