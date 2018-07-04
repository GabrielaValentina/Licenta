package com.example.demo.Domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "number_of_stars")
    private Double numberOfStars;

    @Column(name = "review_type")
    private String reviewType;

    @Column(name = "details")
    private String details;

    @Column(name = "time")
    private Timestamp time;

    public Review() {
    }

    public Review(Long user_id, String userName, String comment, Double numberOfStars, String reviewType, String details, Timestamp time) {
        this.userId = user_id;
        this.userName = userName;
        this.comment = comment;
        this.numberOfStars = numberOfStars;
        this.reviewType = reviewType;
        this.details = details;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUser_id() {
        return userId;
    }

    public void setUser_id(Long user_id) {
        this.userId = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
