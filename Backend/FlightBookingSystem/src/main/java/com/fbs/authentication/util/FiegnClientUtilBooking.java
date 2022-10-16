package com.fbs.authentication.util;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fbs.authentication.models.Passenger;

@FeignClient(value = "Booking-Service", url = "http://localhost:9094/api/v4")
public interface FiegnClientUtilBooking {

	@GetMapping("/allpassengers")
	public ResponseEntity<List<Passenger>> getAllTravellers(@RequestHeader("Authorization") String token);

	@GetMapping("/passenger/{id}")
	public ResponseEntity<?> getPassengerById(@RequestHeader("Authorization") String token,@PathVariable int id);

	@PostMapping("/book/{flightname}")
	public ResponseEntity<?> bookingTicket(@RequestHeader("Authorization") String token,
			@PathVariable String flightname, @RequestBody Passenger passenger);

	@GetMapping("/travelldetails/{eticket}")
	public ResponseEntity<?> travellingDetails(@RequestHeader("Authorization") String token,
			@PathVariable String eticket);

}