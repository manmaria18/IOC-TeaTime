package com.example.iocteatime.repository;

import com.example.iocteatime.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface IRepositoryUser {
    User getUser(String username);

    List<User> getAllUsers();

    void addUser(User user); //createAcount

    void logIn(User user);

    void updateUser(User user, LocalDate lastLogIn);
}
