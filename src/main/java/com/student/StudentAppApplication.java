package com.student;

import com.student.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.mail.MessagingException;

@SpringBootApplication
public class StudentAppApplication {

	//@Autowired
	//private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(StudentAppApplication.class, args);
	}

	/*@EventListener(ApplicationReadyEvent.class)
	public void triggerEmail() throws MessagingException {
		emailService.sendSimpleMessage("senguptatanmoy5@gmail.com",
				"this is body",
				"this is mail with attachment",
				"/Users/dell/Documents/LogicalQuestionBunch.pdf");

	}*/
}