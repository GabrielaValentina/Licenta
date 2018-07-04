package com.example.demo.Domain.Request;

public class DetailsRoutesRequest {
    private String departure;
    private String destination;
    private String date;
    private String time;
    private int number_of_changes;

    public DetailsRoutesRequest(){}

    public DetailsRoutesRequest(String departure, String destination, String date, String time, int number_of_changes) {
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.number_of_changes = number_of_changes;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumber_of_changes() {
        return number_of_changes;
    }

    public void setNumber_of_changes(int number_of_changes) {
        this.number_of_changes = number_of_changes;
    }
}
