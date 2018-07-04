package com.example.demo.Controller;

import com.example.demo.Domain.MessageChat;
import com.example.demo.Domain.Request.MessageChatRequest;

import com.example.demo.Service.Interface.IChatService;

import com.example.demo.Utils.GoogleCloudMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    IChatService chatService;

    @Autowired
    GoogleCloudMessaging googleCloudMessaging;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public   List<MessageChat> sendMessage(  List<MessageChat> message) throws Exception{
       return  message;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

    @RequestMapping(value = "/sendMessage")
    public void sendMessage(@RequestBody MessageChatRequest message){
        MessageChat new_message= chatService.addNewMessage(message.getId_sender(), message.getName(), message.getMessage(), message.getSentAt());
        List<MessageChat> messages = chatService.getAllMessages();
        messagingTemplate.convertAndSend("/topic/greetings", messages);
    }

    @RequestMapping(value = "/getMessages", method = RequestMethod.GET)
    public List<MessageChat> getMessages(){
       return chatService.getAllMessages();
    }
}
