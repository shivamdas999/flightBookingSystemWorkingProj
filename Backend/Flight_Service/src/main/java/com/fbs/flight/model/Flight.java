package com.fbs.flight.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Flight")
public class Flight {
	@Id
	@NotEmpty
	private String flightId;
	@NotEmpty
	private String flightname;
	@NotEmpty
	private String source;
	@NotEmpty
	private String	destiny;
	@NotNull
	@DateTimeFormat(style = "hh:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private LocalTime depart_time;
	@NotNull
	@DateTimeFormat(style = "hh:mm")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	private LocalTime arrival_time;
	@Digits(fraction = 0, integer = 1000, message = "Enter valid seats count")
	private int totalSeats;
	@NotNull(message = "Invalid Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
}






















