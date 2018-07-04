package com.example.demo.Domain.Request;

import java.sql.Timestamp;

public class ReviewRouteRequest {

    private String departure;
    private String arrival;
    private String comment;
    private double stars;
    private String time;
    private String name;

    public ReviewRouteRequest(){}

    public ReviewRouteRequest(String departure, String arrival, String comment, double stars, String timestamp,
                              String name) {
        this.departure = departure;
        this.arrival = arrival;
        this.comment = comment;
        this.stars = stars;
        this.time = timestamp;
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
