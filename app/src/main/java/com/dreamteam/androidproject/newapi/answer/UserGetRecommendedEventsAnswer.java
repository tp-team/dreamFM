package com.dreamteam.androidproject.newapi.answer;

import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/11/2014.
 */

public class UserGetRecommendedEventsAnswer {
    private String status;
    private ObjectList<EventGetInfoAnswer> events = new ObjectList<EventGetInfoAnswer>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ObjectList<EventGetInfoAnswer> getEvents() {
        return events;
    }

    public void setEvents(ObjectList<EventGetInfoAnswer> events) {
        this.events = events;
    }
}
