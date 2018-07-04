package com.example.demo.Domain.Request;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class RouteRequest implements Serializable {
    private Long id;
    private String id_train;
    private Timestamp date;
    private Timestamp delay;
    private List<StationRequest> stations;

    public RouteRequest(){
        this.id = Long.parseLong("-1");
    }

    public RouteRequest(Long id, String id_train, Timestamp date, Timestamp delay, List<StationRequest> stations) {
        this.id = id;
        this.id_train = id_train;
        this.date = date;
        this.delay = delay;
        this.stations = stations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_train() {
        return id_train;
    }

    public void setId_train(String id_train) {
        this.id_train = id_train;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDelay() {
        return delay;
    }

    public void setDelay(Timestamp delay) {
        this.delay = delay;
    }

    public List<StationRequest> getStations() {
        return stations;
    }

    public void setStations(List<StationRequest> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return  "{" +
                "id:" + id +
                ", id_train:'" + id_train + '\'' +
                ", date:" + date +
                ", delay:" + delay +
                ", stations:" + stations +
                '}';
    }
}
