package com.datamatics.api.responseBean;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomeResponse {
	
	private String status;
	
	private int statusCode;
	
	private LocalDateTime localadateTime;

}
