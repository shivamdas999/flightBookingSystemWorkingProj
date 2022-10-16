package com.fbs.flight.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.flight.expection.FlightNotFoundException;
import com.fbs.flight.expection.NoProperDataException;
import com.fbs.flight.expection.ResourceAlreadyExistException;
import com.fbs.flight.model.Flight;
import com.fbs.flight.model.Trip;
import com.fbs.flight.service.FlightService;

import lombok.extern.slf4j.Slf4j;
@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/api/v3")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/allflights")
	public ResponseEntity<List<Flight>> getAllflight() throws FlightNotFoundException {

		List<Flight> flightslist = flightService.getAllFlights();
		log.info("starting  of get mapping");
		if (flightslist!=null) {
			log.debug("Flights are {}" + flightslist);
			return new ResponseEntity<>(flightslist, HttpStatus.OK);
		} else {
			log.debug(" no Flights found ");
			return new ResponseEntity<>(flightslist, HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/flight/{id}")
	public ResponseEntity<Flight> getFlightById(@Valid @PathVariable String id) throws FlightNotFoundException
	{
		log.info("starting  of get mapping");
		Flight flights = flightService.getflightById(id);

		if (flights.getFlightId() != null) {
			log.debug("flight is {}" + flights);
			return new ResponseEntity<>(flights, HttpStatus.OK);
		} else {
			log.debug(" no flight found ");
			return new ResponseEntity<>(flights, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path="/addflight")
	public ResponseEntity<Flight> addflight(@Valid @RequestBody Flight flight)
			throws NoProperDataException, ResourceAlreadyExistException {
		if (flight != null) {

			flightService.addflight(flight);
			log.debug("Flight added");
			return new ResponseEntity<>(flight, HttpStatus.CREATED);

		} else {
			throw new NoProperDataException("Please fill fields");
		}
	}

	@DeleteMapping(path = "/delete/flight/{id}")
	public ResponseEntity<?> deleteflight(@Valid @PathVariable String id) throws FlightNotFoundException {
		int count = 1;
		for (int i = 1; i >= count; count++) {
			if (count == 1)
				flightService.deleteFlight(id);
			else
				log.info("id not found");
		}
		return ResponseEntity.ok(" deleted operation done ");
	}

	@PostMapping(path = "/search")
	public ResponseEntity<?> searchFlight(@Valid @RequestBody Trip trip) throws FlightNotFoundException {
		try {
			return new ResponseEntity<>(flightService.searchFlight(trip), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No flight available", HttpStatus.NOT_FOUND);
		}
	}
}
