package com.fbs.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fbs.booking.exception.ErrorDetails;
import com.fbs.booking.exception.NoProperDataException;
import com.fbs.booking.exception.PassangerNotFoundException;
import com.fbs.booking.model.Passenger;

@Service
public interface BookingService {

	public Passenger bookingTicket(String flightid, Passenger passenger) throws NoProperDataException;

	public Passenger travellingDetails(String ticket) throws NoProperDataException;

	public List<Passenger> getAllPassengers()throws PassangerNotFoundException;

	public Passenger getPassengerById(int id) throws PassangerNotFoundException;

}
