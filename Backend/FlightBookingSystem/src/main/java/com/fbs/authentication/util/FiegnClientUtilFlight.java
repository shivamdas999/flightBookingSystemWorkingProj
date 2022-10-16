package com.fbs.authentication.util;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fbs.authentication.models.Flight;
import com.fbs.authentication.models.Trip;

@FeignClient(value = "Flight-Service", url = "http://localhost:9092/api/v3")
public interface FiegnClientUtilFlight {

	@GetMapping("/allflights")
	public ResponseEntity<List<Flight>> getAllFlights(@RequestHeader("Authorization") String token);

	@GetMapping("/flight/{id}")
	public ResponseEntity<Flight> getFlightById(@RequestHeader("Authorization") String token, @PathVariable String id);

//	@PutMapping("/updateflight/{id}")
//	public ResponseEntity<Flight> updateFlight(Flight flight, @PathVariable String id);

	@DeleteMapping(path = "/delete/flight/{id}")
	public ResponseEntity<String> deleteFlight(@RequestHeader("Authorization") String token, @PathVariable String id);

	@PostMapping("/addflight")
	public ResponseEntity<Flight> addFlights(@RequestHeader("Authorization") String token, @RequestBody Flight newflight);

	@GetMapping("/search")
	public ResponseEntity<Flight> searchFlight(@RequestHeader("Authorization") String token,Trip trip);
}