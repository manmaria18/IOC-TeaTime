package com.example.iocteatime.service;

public interface IServiceGuests {
    void joinEvent(int eventId, String username);
    void leaveEvent(int eventId, String username);
}
