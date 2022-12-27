package com.example.iocteatime.service;

import com.example.iocteatime.domain.Event;
import com.example.iocteatime.domain.User;
import com.example.iocteatime.repository.RepositoryEvents;
import com.example.iocteatime.repository.RepositoryUser;

public class ServiceGuests implements IServiceGuests{

    private RepositoryEvents repoEvents;
    private RepositoryUser repoUsers;

    public ServiceGuests(RepositoryEvents repoEvents, RepositoryUser repoUsers) {
        this.repoEvents=repoEvents;
        this.repoUsers=repoUsers;
    }

    @Override
    public void joinEvent(int eventId, String username,String enteredBy) {
        Event event = repoEvents.getEventById(eventId);
        User user = repoUsers.getUser(username);
        repoEvents.joinEvent(event,user,enteredBy);
    }

    @Override
    public void leaveEvent(int eventId, String username) {
        repoEvents.leaveEvent(eventId, username);
    }
}
