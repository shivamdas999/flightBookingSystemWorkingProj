package com.fbs.booking.service;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.booking.exception.NoProperDataException;
import com.fbs.booking.exception.PassangerNotFoundException;
import com.fbs.booking.model.Passenger;
import com.fbs.booking.repository.FlightRepository;
import com.fbs.booking.repository.PassengerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceIpm implements BookingService {
	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Override
	public Passenger bookingTicket(String flightid, Passenger passenger) throws NoProperDataException {
		passenger.setFlight(flightRepository.findByFlightId(flightid));
		passenger.setEticket(flightid + passenger.getPassengerid());
		passengerRepository.insert(passenger);
		return passenger;
	}

	@Override
	public Passenger travellingDetails(String token) throws NoProperDataException {
		Passenger travller = passengerRepository.findByEticket(token);
		return travller;
	}

	@Override
	public List<Passenger> getAllPassengers() throws PassangerNotFoundException {
		log.info("get all passengers");
		List<Passenger> passengers = passengerRepository.findAll();
		return passengers;
	}

	@Override
	public Passenger getPassengerById(int id) throws PassangerNotFoundException {
		Passenger psgrs = passengerRepository.findByPassengerid(id);
		log.debug("customers getbyid {}", psgrs);
		return psgrs;

	}
}

//flightRepository.findByFlightId(flightid).getDate().toString().replaceAll("[^0-9]","")
