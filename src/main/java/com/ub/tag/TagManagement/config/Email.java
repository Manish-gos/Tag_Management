package com.ub.tag.TagManagement.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class Email {

		@Autowired
	    private JavaMailSender javaMailSender;
		
		public void sendEmail(String mail, String message) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(mail);
	        msg.setSubject("Registration confirmation mail");
	        msg.setText(message);

	        javaMailSender.send(msg);

	    }
}
