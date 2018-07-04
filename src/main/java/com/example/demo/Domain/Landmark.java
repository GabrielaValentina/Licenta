package com.example.demo.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "landmark")
public class Landmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    //@ManyToOne
   // @JoinColumn(name = "route_id")
   // @JsonIgnoreProperties("landmarks")
   // private Route route;

    @Column(name = "route_id")
    private Long route;

    public Landmark(){}

    public Landmark(Float latitude, Float longitude, String location, String description, Long route) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.description = description;
        this.route = route;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRoute() {
        return route;
    }

    public void setRoute(Long route) {
        this.route = route;
    }
}
