package com.example.iocteatime.service;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;

import java.util.List;

public interface IServiceGuests {
    void joinEvent(int eventId, String username, String enteredBy);
    void leaveEvent(int eventId, String username);

    List<String> getEventsNotifications(String username);
}
