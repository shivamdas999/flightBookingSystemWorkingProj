package com.fbs.booking.model;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "passenger")
public class Passenger {
	
	@Transient
	public static final String SEQUENCE_NAME = "passenger_sequence";
	@Id
	private int passengerid;
	@NotEmpty
	@Size(min=3,message = "Enter a valid name")
	private String firstname;
	@NotEmpty
	private String lastname;
	@NotEmpty(message = "Please enter ur gender")
	private String gender;
	@Positive(message = "Invalid age")
	@Min(1)
	@Max(100)
	private int age;
	private String eticket;
	private Flight flight;
	
}