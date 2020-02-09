package com.datamatics.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.datamatics.api.service.INotificationService;

@SpringBootApplication
@EnableScheduling
public class NotificationServiceApplication {
    @Autowired
	private INotificationService notoficationService;
	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
	
	
    @Scheduled(fixedDelay = 5000)
    public void notificatinService(){     
        	notoficationService.mailNotification();
          
    }
    

    



}
