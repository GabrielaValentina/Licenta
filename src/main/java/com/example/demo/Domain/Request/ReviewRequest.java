package com.example.demo.Domain.Request;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReviewRequest implements Serializable{
    private int id;
    private Long id_user;
    private String name;
    private String comment;
    private Double numberOfStars;
    private String review_type;
    private String review_param;
    private String time;

    public ReviewRequest(){}

    public ReviewRequest( Long id_user, String name, String comment, Double numberOfStars, String review_type, String review_param, String time) {
        this.id_user = id_user;
        this.name = name;
        this.comment = comment;
        this.numberOfStars = numberOfStars;
        this.review_type = review_type;
        this.review_param = review_param;
        this.time = time;
    }

    public ReviewRequest(int id, Long id_user, String name, String comment, Double numberOfStars, String review_type, String review_param, String time) {
        this.id = id;
        this.id_user = id_user;
        this.name = name;
        this.comment = comment;
        this.numberOfStars = numberOfStars;
        this.review_type = review_type;
        this.review_param = review_param;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Double numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getReview_type() {
        return review_type;
    }

    public void setReview_type(String review_type) {
        this.review_type = review_type;
    }

    public String getReview_param() {
        return review_param;
    }

    public void setReview_param(String review_param) {
        this.review_param = review_param;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
