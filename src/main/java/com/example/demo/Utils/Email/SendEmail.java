package com.example.demo.Utils.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
    public class SendEmail {

        @Value("${server-url}")
        private String serverURL;

        @Autowired
        private JavaMailSender javaMailSender;

        public void sendEmail(String email_address, String code){
            String subject = "Confirmare înregistrare în aplicația TrainApp!";
            String message = "Codul de înregistrare este următorul: " + code;

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(email_address);
            email.setFrom("trainapp@gmail.com");
            email.setSubject(subject);
            email.setText(message);
            javaMailSender.send(email);
        }




        public void sendReservationNotification(String email_address, String code){
            String recipientAddress = email_address;
            String subject = "Confirmare înregistrare în aplicația TrainApp!";
            String message = "Codul de înregistrare este următorul: " + code;

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setFrom("gabrielavali97@gmail.com");
            email.setSubject(subject);
            email.setText(message);
            javaMailSender.send(email);

        }




    }

