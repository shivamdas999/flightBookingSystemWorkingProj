package com.fbs.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fbs.booking.model.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String>{

	List<Flight> findAllBySource(String source);
	List<Flight> findAllByDestiny(String destiny);
	List<Flight> findAllByDate(LocalDate date);
	Flight findByFlightId(String flightId);
}
