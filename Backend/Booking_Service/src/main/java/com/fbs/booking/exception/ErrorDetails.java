package com.fbs.booking.exception;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ErrorDetails {
    
	private String ErrorMessage;
	private LocalDateTime TimeStamp;
	
	
}
