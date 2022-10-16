package com.fbs.flight.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.fbs.flight.expection.FlightNotFoundException;
import com.fbs.flight.expection.ResourceAlreadyExistException;
import com.fbs.flight.model.Flight;
import com.fbs.flight.model.Trip;

@Service
public interface FlightService {

	public List<Flight> getAllFlights();

	public Object deleteFlight(@Valid String id);

	public Flight addflight( Flight flight) throws ResourceAlreadyExistException;

	public Flight getflightById(@Valid String id) throws FlightNotFoundException;

	public Object searchFlight(Trip trip);
}
