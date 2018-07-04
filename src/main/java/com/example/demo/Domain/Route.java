package com.example.demo.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRoute;

    @Column(name = "id_train")
    private String idTrain;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "delay")
    private Timestamp delay;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("route_id")
    public Set<StationOfARoute> stationOfARoutes;

   // @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
   // @JsonIgnoreProperties("route_id")
   // public Set<Landmark> landmarks;

    public Route() {
        this.idTrain = "";
    }

    public Route(String id_train, Timestamp date, Timestamp delay) {
        this.idTrain = id_train;
        this.date = date;
        this.delay = delay;
    }

    public Long getId_route() {
        return idRoute;
    }

    public void setId_route(Long id_route) {
        this.idRoute = id_route;
    }

    public String getId_train() {
        return idTrain;
    }

    public void setId_train(String id_train) {
        this.idTrain = id_train;
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

    public Set<StationOfARoute> getStationOfARoutes() {
        return stationOfARoutes;
    }

    public void setStationOfARoutes(Set<StationOfARoute> stationOfARoutes) {
        this.stationOfARoutes = stationOfARoutes;
    }

}
