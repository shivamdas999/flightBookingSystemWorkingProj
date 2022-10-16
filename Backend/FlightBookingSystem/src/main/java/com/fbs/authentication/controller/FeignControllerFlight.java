package com.fbs.authentication.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.authentication.exception.FlightNotFoundException;
import com.fbs.authentication.exception.NoProperDataException;
import com.fbs.authentication.models.Flight;
import com.fbs.authentication.models.Trip;
import com.fbs.authentication.util.FiegnClientUtilFlight;


@RestController
@RequestMapping("api/v3")
public class FeignControllerFlight {

	@Autowired
	private FiegnClientUtilFlight feignFlight;
	

	@GetMapping("/allflights") 
	@PreAuthorize( "hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Flight>> getAllFlights(@RequestHeader("Authorization") String token) throws FlightNotFoundException
	{
		return feignFlight.getAllFlights(token);
	}
	
	
	@GetMapping("/flight/{id}")
	@PreAuthorize( "hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Flight> getFlightById(@RequestHeader("Authorization") String token, @PathVariable  String id)
	throws FlightNotFoundException{
		return feignFlight.getFlightById(token,id);
	}
	
	
	@PostMapping("/addflight") 
	@PreAuthorize( "hasRole('ROLE_ADMIN')")
	public ResponseEntity<Flight> addFlight(@RequestHeader("Authorization") String token, @RequestBody Flight newflight)  throws NoProperDataException
	{
		return feignFlight.addFlights(token ,newflight);
	}

	
//	@PutMapping("/updateflight/{id}")
//	public ResponseEntity<Flight> updateFlight( @RequestBody Flight flight,@PathVariable String id) throws FlightNotFoundException
//	{
//	return feignFlight.updateFlight(flight, id);
//	}
	
	@GetMapping("/search")
	public ResponseEntity<Flight> searchFlight(@RequestHeader("Authorization") String token, @RequestBody Trip trip) throws FlightNotFoundException{
		return feignFlight.searchFlight(token, trip);
	}
	@DeleteMapping(path="/delete/flight/{id}")
	public ResponseEntity<String> deleteFlight(@Valid @RequestHeader("Authorization") String token,@PathVariable String id) throws FlightNotFoundException {
			return feignFlight.deleteFlight(token,id);
}

	
}