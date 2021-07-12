package com.bootcampProject.BootcampProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMail extends BaseService{

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String subject, String text, String sentTo){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(text);
        mailMessage.setSubject(subject);
        mailMessage.setTo(sentTo);
        javaMailSender.send(mailMessage);
    }
}
