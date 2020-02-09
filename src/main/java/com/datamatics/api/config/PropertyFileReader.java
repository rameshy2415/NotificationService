package com.datamatics.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Configuration
@PropertySource("classpath:urlConfig.properties")
@Getter
@Setter
public class PropertyFileReader {
	 @Value("${prodUrl}")
	  private String urlName;
}
