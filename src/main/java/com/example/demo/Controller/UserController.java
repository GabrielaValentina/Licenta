package com.example.demo.Controller;

import com.example.demo.Domain.AccountCode;
import com.example.demo.Domain.Request.UserRequest;
import com.example.demo.Domain.User;
import com.example.demo.Service.Interface.IUserService;
import com.example.demo.Utils.Email.SendEmail;

import com.example.demo.Utils.WebCrawler.Spider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    SendEmail emailServer;

    @RequestMapping(value = "/sendEmail/{email_address}/{code}")
    public String sendMsg(@PathVariable("email_address") String email_address, @PathVariable("code") String code) {
        emailServer.sendReservationNotification(email_address, code);
        return "Codul a fost trimis la adresa de email: " + email_address;
    }

    @RequestMapping(value = "/getUsersConfirmated", method = RequestMethod.GET)
    public List<User> getAllConf() {
        return userService.getAllUsersWithConfirmatedAccount();
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User addUser(@RequestBody UserRequest userRequest) {
        return userService.addNewUser(userRequest.getLastName(), userRequest.getFirstName(), userRequest.getEmailAddress(),
                userRequest.getPassword(), userRequest.getPhoneNumber());
    }

    @RequestMapping(value = "/login/{email_address}/{password}")
    public User login(@PathVariable("email_address") String emailAddress, @PathVariable("password") String password) {
        User user = userService.findUserByEmailAddressAndPassword(emailAddress, password);
        if (user != null)
            return user;
        else
            return new User();
    }

    @RequestMapping(value = "/updatePassword/{email_address}/{new_password}")
    public String updadeUser(@PathVariable("email_address") String emailAddress, @PathVariable("new_password") String new_password) {
        int updated = 0;
        User user = userService.findUserByEmailAddress(emailAddress);
        if (user != null) {
            user.setPassword(new_password);
            userService.addNewUser(user.getLastName(), user.getFirstName(), user.getEmailAddress(),
                    user.getPassword(), user.getPhoneNumber());
            updated = 1;
        }
        return updated + "";
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public String addNewUser(@RequestBody UserRequest userRequest) {
        String message = "";
        if(userService.findUserByEmail(userRequest.getEmailAddress()) != null)
            message = "nu";
        else {
           if( userService.addNewAccount(userRequest) != null)
               emailServer.sendReservationNotification(userRequest.getEmailAddress(), userRequest.getCode());
            message = "da";
        }
        System.out.println(message);
        return message;
    }

    @RequestMapping(value = "/verificationCode/{email_address}/{code}", method = RequestMethod.GET)
    public String verificationCode(@PathVariable("email_address") String emailAddress, @PathVariable("code") String code) {
       String message;
        if(userService.confirmationAccount(emailAddress, code).getId() != -1)
            message = "true";
        else
            message = "false";
        System.out.println(message);
        return message;
    }
}


