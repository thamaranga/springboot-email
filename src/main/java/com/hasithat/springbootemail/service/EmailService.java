package com.hasithat.springbootemail.service;

import com.hasithat.springbootemail.dto.EmailRequest;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleEmail(EmailRequest emailRequest){
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(emailRequest.getToEmail());
        simpleMailMessage.setSubject(emailRequest.getSubject());
        simpleMailMessage.setText(emailRequest.getMessageBody());
        javaMailSender.send(simpleMailMessage);
        return "email sent successfully "+emailRequest.getToEmail();

    }

    public String sendEmailWithAttachment(EmailRequest emailRequest) throws MessagingException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(emailRequest.getToEmail());
        mimeMessageHelper.setSubject(emailRequest.getSubject());
        mimeMessageHelper.setText(emailRequest.getMessageBody());
        FileSystemResource file= new FileSystemResource(new File(emailRequest.getFilename()));
        mimeMessageHelper.addAttachment(file.getFilename(), file);
        javaMailSender.send(mimeMessage);
        return "email sent successfully "+emailRequest.getToEmail();

    }

}
