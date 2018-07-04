package com.example.demo.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "station_of_a_route")
public class StationOfARoute implements Comparable<StationOfARoute> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStationRoute;

    @Column(name = "station_name")
    private String stationName;

    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "station_number")
    private int stationNumber;

    @ManyToOne
    @JoinColumn(name = "route_id")
    @JsonIgnoreProperties("stationOfARoutes")
    private Route route;

    public StationOfARoute() {
    }

    public StationOfARoute(String stationName, Timestamp arrivalTime, Timestamp departureTime, int stationNumber, Route route) {
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stationNumber = stationNumber;
        this.route = route;
    }

    public Long getIdStationRoute() {
        return idStationRoute;
    }

    public void setIdStationRoute(Long idStationRoute) {
        this.idStationRoute = idStationRoute;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "StationOfARoute{" +
                "idStationRoute=" + idStationRoute +
                ", stationName='" + stationName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", stationNumber=" + stationNumber +
                ", route=" + route +
                '}';
    }

    @Override
    public int compareTo(StationOfARoute o) {
        if(o.getStationNumber() < this.getStationNumber())
            return 1;
        else
            if(o.getStationNumber() > this.getStationNumber())
                return -1;
        else
            return 0;
    }
}
