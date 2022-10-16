package com.fbs.authentication.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.authentication.exception.FlightNotFoundException;
import com.fbs.authentication.exception.NoProperDataException;
import com.fbs.authentication.exception.PassangerNotFoundException;
import com.fbs.authentication.models.Passenger;
import com.fbs.authentication.util.FiegnClientUtilBooking;

@RestController
@RequestMapping("/api/v4")
public class FeignControllerBooking {

	@Autowired
	private FiegnClientUtilBooking feignBooking;

	@GetMapping("/allpassengers")
	@PreAuthorize( "hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllTravellers(@RequestHeader("Authorization") String token)
			throws PassangerNotFoundException {

		return feignBooking.getAllTravellers(token);

	}

	@GetMapping("/passenger/{id}")
	@PreAuthorize( "hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getPassengerById(@RequestHeader("Authorization") String token, @PathVariable int id)
			throws PassangerNotFoundException  {

		return feignBooking.getPassengerById(token, id);
	}

	@PostMapping("/book/{flightname}")
	@PreAuthorize( "hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> bookingTicket(@RequestHeader("Authorization") String token,
			@PathVariable String flightname, @RequestBody Passenger passenger)
			throws FlightNotFoundException, NotFoundException, NoProperDataException {
		return feignBooking.bookingTicket(token, flightname, passenger);
	}

	@GetMapping("/travelldetails/{eticket}")
	@PreAuthorize( "hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> travellingDetails(@RequestHeader("Authorization") String token,
			@PathVariable String eticket) throws NotFoundException {
		return feignBooking.travellingDetails(token, eticket);
	}

}