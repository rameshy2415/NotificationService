package com.datamatics.api.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.datamatics.api.config.PropertyFileReader;
import com.datamatics.api.responseBean.CustomeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificationServiceImpl implements INotificationService{
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EmailNotificationService emailService;
	
	@Autowired
	private PropertyFileReader propValue;
	
	//private static final String url="http://localhost:8081/actuator/health";
	private static final String url="";
	
	private static  boolean flag=true;

	@Override
	public void mailNotification() {
         List<String> urlList= new ArrayList<>();    
        CustomeResponse response = null;
        LocalDateTime dateTime = LocalDateTime.now();
		try {			
			String striongResponse=restTemplate.getForObject(url, String.class);
			response = new ObjectMapper().readValue(striongResponse, CustomeResponse.class);
			if(isServiceDown(response)) {
				System.out.println("Service is down.............. "+dateTime);
			}else {
				 System.out.println("Service is UP & Running........ "+dateTime);
			}
		} catch (Exception e) {
			System.out.println("Service is down........... "+dateTime);
			urlList.add(propValue.getUrlName());
			if(flag) {
				flag=false;
				String mailRes=emailService.sendMail(urlList);
				System.out.println(mailRes);
			}
			
		}
	}
	
    private boolean isServiceDown(CustomeResponse response) {
		boolean flag=true;
    	if("UP".equalsIgnoreCase(response.getStatus())) {
			flag=false;
		}
		return flag;
	}
    
}
