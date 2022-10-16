package com.fbs.booking.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.booking.exception.ErrorDetails;
import com.fbs.booking.exception.FlightNotFoundException;
import com.fbs.booking.exception.NoProperDataException;
import com.fbs.booking.exception.PassangerNotFoundException;
import com.fbs.booking.model.Passenger;
import com.fbs.booking.service.BookingService;
import com.fbs.booking.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;
@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/api/v4")
public class BookingController {
	@Autowired

	private BookingService bookingService;
	@Autowired
	private SequenceGeneratorService service;
	@Autowired
	private ErrorDetails msg;

	@GetMapping("/allpassengers")
	public ResponseEntity<?> getAllTravellers() throws NotFoundException, PassangerNotFoundException {

		List<Passenger> passangers = bookingService.getAllPassengers();
		log.info("starting  of get mapping");

		if (passangers.size() > 0) {
			log.debug("Passangers are {}" + passangers);
			return new ResponseEntity<>(passangers, HttpStatus.OK);
		} else {
			log.debug(" no Passangers found ");
			msg.setErrorMessage("No passenger found");
			msg.setTimeStamp(LocalDateTime.now());
			return new ResponseEntity<>(msg, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/passenger/{id}")
	public ResponseEntity<?> getPassengerById(@PathVariable int id)
			throws NotFoundException, PassangerNotFoundException, Exception {

		log.info("starting  of get mapping");
		Passenger passengers = bookingService.getPassengerById(id);

		if (passengers != null) {
			log.debug("Passengers are {}" + passengers);
			return new ResponseEntity<>(passengers, HttpStatus.OK);
		} else {
			log.debug(" no Passengers found ");
			msg.setErrorMessage("No passenger found");
			msg.setTimeStamp(LocalDateTime.now());
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/book/{flightid}")
	public ResponseEntity<?> bookingTicket(@PathVariable String flightid , @Valid @RequestBody Passenger passenger) throws NoProperDataException{

		if (passenger != null) {

			passenger.setPassengerid(service.getSequenceNumberForCustomer(Passenger.SEQUENCE_NAME));
			bookingService.bookingTicket(flightid, passenger);
			log.error("added passenger");
			return new ResponseEntity<>(passenger, HttpStatus.CREATED);
		} else {
			throw new NoProperDataException("Please fill fields");
		}
	}

	@GetMapping("/travelldetails/{eticket}")
	public ResponseEntity<?> travellingDetails(@PathVariable String eticket)
			throws NotFoundException, PassangerNotFoundException, NoProperDataException {
		log.info("starting  of get mapping");

		Passenger passengers = bookingService.travellingDetails(eticket);

		if (passengers != null) {
			log.debug("Passengers are {}" + passengers);
			return new ResponseEntity<>(passengers, HttpStatus.OK);
		} else {
			log.debug(" no Passengers found ");
			msg.setErrorMessage("No passenger found");
			msg.setTimeStamp(LocalDateTime.now());
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		}

	}
}
