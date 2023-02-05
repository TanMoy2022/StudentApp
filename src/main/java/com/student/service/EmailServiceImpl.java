package com.student.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;


    @Override
    public void sendSimpleMessage(String toEmail,
                                  String body, String subject,
                                  String attachment) throws MessagingException {


        MimeMessage mimeMessage=emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("senguptatanmoy805@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystemResource=new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        emailSender.send(mimeMessage);

        System.out.println("Mail with attachment sent successfully");


    }
}
