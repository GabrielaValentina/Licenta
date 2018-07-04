package com.example.demo.Service;

import com.example.demo.Domain.MessageChat;
import com.example.demo.Repository.MessageChatRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.Interface.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ChatService implements IChatService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageChatRepository messageChatRepository;


    @Override
    public MessageChat addNewMessage(int id_sender, String name, String message, String sent_at) {
        Timestamp timest = Timestamp.valueOf(sent_at);
        MessageChat new_message = new MessageChat(id_sender, name, message, timest);
        return messageChatRepository.save(new_message);
    }

    @Override
    public List<MessageChat> getAllMessages() {
        return messageChatRepository.findAll();
    }


}
