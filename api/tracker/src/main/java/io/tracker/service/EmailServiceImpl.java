package io.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.tracker.domain.Alert;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmailAlert(Alert existingAlert) {
		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setFrom("mailsender9009@gmail.com");
		mail.setTo("kaushik9nandhan@gmail.com");
		mail.setSubject("Car tracker HIGH Alert");
		mail.setText("Hello Admin, "+existingAlert.getPriority()+" Alert found for the vehicle with VIN "+existingAlert.getVin()+". Reason: " + existingAlert.getMessage());
		javaMailSender.send(mail);
	}

}
