package com.example.demo.Domain.Request;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageChatRequest implements Serializable{

    private int id_sender;
    private String name;
    private String message;
    private String sentAt;

    public MessageChatRequest() {
    }

    public MessageChatRequest(int id_sender, String name, String message, String sentAt) {
        this.id_sender = id_sender;
        this.name = name;
        this.message = message;
        this.sentAt = sentAt;
    }

    public int getId_sender() {
        return id_sender;
    }

    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
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

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
}
