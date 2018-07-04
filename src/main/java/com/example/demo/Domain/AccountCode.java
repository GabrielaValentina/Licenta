package com.example.demo.Domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "account_code")
public class AccountCode {

    private static final int EXPIRATION = 60 * 24; //60*24

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "code")
    private String code;

    @Column(name = "expiration_date")
    private Date expirationDate;

    public AccountCode(){}

    public AccountCode(User user, String code) {
        this.user = user;
        this.code = code;
        this.expirationDate = calculateExpirationDate(EXPIRATION);
    }

    private Date calculateExpirationDate(final int expirationTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expirationTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
