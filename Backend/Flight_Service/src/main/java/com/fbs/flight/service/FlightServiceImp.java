package com.fbs.flight.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.flight.expection.FlightNotFoundException;
import com.fbs.flight.expection.ResourceAlreadyExistException;
import com.fbs.flight.model.Flight;
import com.fbs.flight.model.Trip;
import com.fbs.flight.repository.FlightRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightServiceImp implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Override
	public List<Flight> getAllFlights() {
		log.info("get all flights");
		return flightRepository.findAll();
	}

	@Override
	public Object deleteFlight(@Valid String id) {
		log.info("Start delete");

		Flight removeflight = flightRepository.findByFlightId(id);
		if (removeflight != null) {
			flightRepository.deleteById(id);
			log.debug("deleted successfully {}", removeflight);
		} else {
			return new FlightNotFoundException("Id Not Available");
		}
		log.info(" deleted End");
		return removeflight;
	}

	@Override
	public Flight addflight(@Valid Flight flight) throws ResourceAlreadyExistException {
		if (flightRepository.findByFlightId(flight.getFlightId()) != null)
			throw new ResourceAlreadyExistException("This Flight Id Already Exist" + flight.getFlightId());
		flightRepository.insert(flight);
		return flight;
	}

	@Override
	public Flight getflightById(@Valid String id) throws FlightNotFoundException {
		Flight flight = flightRepository.findById(id)
				.orElseThrow(() -> new FlightNotFoundException("No Flight Exist With This Id : " + id));
		return flight;
	}

	public Object searchFlight(Trip trip) {
		List<Flight> destiny = flightRepository.findAllByDestiny(trip.getDestiny());
		List<Flight> source = flightRepository.findAllBySource(trip.getSource());
		List<Flight> date = flightRepository.findAllByDate(trip.getDate());
		List<Flight> available = source.stream()
				.filter((destiny.stream().filter(date::contains).collect(Collectors.toList()))::contains)
				.collect(Collectors.toList());
		return available;
	}
}
