package com.hasithat.springbootemail.controller;

import com.hasithat.springbootemail.dto.EmailRequest;
import com.hasithat.springbootemail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSendController {

    @Autowired
    EmailService emailService;

    @PostMapping("/sendSimpleEmail")
    public String sendSimpleEmail(@RequestBody EmailRequest emailRequest){
        return emailService.sendSimpleEmail(emailRequest);
    }

    @PostMapping("/sendEmailWithAttachment")
    public String sendEmailWithAttachment(@RequestBody EmailRequest emailRequest){
        try {
            return emailService.sendEmailWithAttachment(emailRequest);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Error occured while sending email";
    }
}
