package com.example.demo.Domain.Request;

public class LandmarkRequest {

    private Float latitude;
    private Float longitude;
    private String location;
    private String description;
    private Long route_id;

    public LandmarkRequest(){}

    public LandmarkRequest(Float latitude, Float longitude, String location, String description, Long route_id) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.description = description;
        this.route_id = route_id;
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

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }
}
