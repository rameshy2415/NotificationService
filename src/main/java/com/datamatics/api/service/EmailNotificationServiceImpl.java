package com.datamatics.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {
	@Autowired
	private JavaMailSender javaMailSender;
	

	@Value("${spring.mail.to}")
	private String to;
	
	@Value("${spring.mail.from}")
	private String from;
	
	@Value("${spring.mail.cc}")
	private String cc;
	

	@Value("${spring.mail.bcc}")
	private String bcc;

	@Override
	public String sendMail(List<String> url) {
		String response = "Mail sent successfully..";
		LocalDateTime curr= LocalDateTime.now();
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(to,bcc);
			msg.setCc(cc);
			msg.setBcc(bcc);
			msg.setSubject("SPRING_SERVICE_DOWN  "+curr);
			msg.setText(" Hello Team, \n\n" 
					+"=>"+ url.get(0)+"\n\n\n NOTE:The Above service is down.... "
					+"\n\n\n\n Thanks & Regards,\n doNotReply@datamatics.com");
			javaMailSender.send(msg);

		} catch (Exception e) {
			response = "Exception Occurred during sent a mail";
		}
		return response;
	}

}
