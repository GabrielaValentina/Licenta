package com.example.demo.Service.Interface;

import com.example.demo.Domain.MessageChat;

import java.sql.Timestamp;
import java.util.List;

public interface IChatService {
    MessageChat addNewMessage(int id_sender, String name, String message, String sent_at);
    List<MessageChat> getAllMessages();

}
