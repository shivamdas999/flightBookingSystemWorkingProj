package com.fbs.flight.expection;

import java.time.LocalDateTime;


import lombok.Data;

@Data
public class ErrorDetails {
    
	private String ErrorMessage;
	private LocalDateTime TimeStamp;
	
	
}
