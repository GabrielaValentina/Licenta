package com.example.demo.Domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
public class MessageChat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "id_sender")
    private int idSender;

    @Column (name = "name")
    private String name;

    @Column(name = "message")
    private String message;

    @Column(name = "sent_at")
    Timestamp sentAt;

    public MessageChat() {
    }

    public MessageChat(int idSender, String name, String message, Timestamp sentAt) {
        this.idSender = idSender;
        this.name = name;
        this.message = message;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }
}
