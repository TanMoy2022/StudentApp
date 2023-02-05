package com.student.service;

import javax.mail.MessagingException;

public interface EmailService {

    public void sendSimpleMessage(String toEmail, String body, String subject, String attachment) throws MessagingException;

}
