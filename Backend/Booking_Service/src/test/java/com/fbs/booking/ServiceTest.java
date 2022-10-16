package com.fbs.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fbs.booking.exception.NoProperDataException;
import com.fbs.booking.exception.PassangerNotFoundException;
import com.fbs.booking.model.Flight;
import com.fbs.booking.model.Passenger;
import com.fbs.booking.repository.FlightRepository;
import com.fbs.booking.repository.PassengerRepository;
import com.fbs.booking.service.BookingServiceIpm;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

	@InjectMocks
	private BookingServiceIpm bookingService;

	@Mock
	private PassengerRepository passengerRepository;

	@Mock
	private FlightRepository flightRepository;

	@Test
	@DisplayName("Adding Passenger")
	void save() throws NoProperDataException {
		Flight fly = new Flight();
		fly.setArrival_time(LocalTime.now());
		fly.setDate(LocalDate.of(2022, Month.JANUARY, 12));
		fly.setDepart_time(LocalTime.NOON);
		fly.setDestiny("Tumkur");
		fly.setFlightId("ABUS1");
		fly.setFlightname("AIRBUS");
		fly.setSource("Bengaluru");

		Passenger psngr = new Passenger();
		psngr.setAge(30);
		psngr.setEticket("ASD555555Lk");
		psngr.setFirstname("Akshay");
		psngr.setFlight(fly);
		psngr.setGender("male");
		psngr.setLastname("kumar");
		psngr.setPassengerid(1);

		when(passengerRepository.save(any(Passenger.class))).thenReturn(psngr);

		Passenger newpas = bookingService.bookingTicket("ARBUS", psngr);

		assertNotNull(newpas);
		assertThat(newpas.getFirstname()).isEqualTo("Akshay");

	}
	
	
	@Test
	@DisplayName("Showing all")
	void  getall() throws PassangerNotFoundException {
		Flight fly = new Flight();
		fly.setArrival_time(LocalTime.now());
		fly.setDate(LocalDate.of(2022, Month.JANUARY, 12));
		fly.setDepart_time(LocalTime.NOON);
		fly.setDestiny("Tumkur");
		fly.setFlightId("ABUS1");
		fly.setFlightname("AIRBUS");
		fly.setSource("Bengaluru");

		Passenger psngr = new Passenger();
		psngr.setAge(30);
		psngr.setEticket("ASD555555Lk");
		psngr.setFirstname("Akshay");
		psngr.setFlight(fly);
		psngr.setGender("male");
		psngr.setLastname("kumar");
		psngr.setPassengerid(2);
		
		Passenger psngr1 = new Passenger();
		psngr1.setAge(30);
		psngr1.setEticket("ASD555555Lk");
		psngr1.setFirstname("Akshay");
		psngr1.setFlight(fly);
		psngr1.setGender("male");
		psngr1.setLastname("kumar");
		psngr1.setPassengerid(4);
		
		List<Passenger> list = new ArrayList<Passenger>();
		list.add(psngr1);
		list.add(psngr);
		when(passengerRepository.findAll()).thenReturn(list);
		
		List<Passenger> passengers = bookingService.getAllPassengers();
		
		assertEquals(2, passengers.size());
		assertNotNull(passengers);
	}
	@Test
	@DisplayName("Showing By id")
	void getbyid() throws PassangerNotFoundException {
		Flight fly = new Flight();
		fly.setArrival_time(LocalTime.now());
		fly.setDate(LocalDate.of(2022, Month.JANUARY, 12));
		fly.setDepart_time(LocalTime.NOON);
		fly.setDestiny("Tumkur");
		fly.setFlightId("ABUS1");
		fly.setFlightname("AIRBUS");
		fly.setSource("Bengaluru");
		Passenger psngr = new Passenger();
		psngr.setAge(30);
		psngr.setEticket("ASD555555Lk");
		psngr.setFirstname("Akshay");
		psngr.setFlight(fly);
		psngr.setGender("male");
		psngr.setLastname("kumar");
		psngr.setPassengerid(4);
		
		when(passengerRepository.findById(any())).thenReturn(Optional.of(psngr));
		Passenger extpass = bookingService.getPassengerById(4);

		assertNotNull(extpass);
		assertThat(extpass.getFirstname()).isEqualTo("Akshay");
	}

	@Test
	@DisplayName("Showing Details")
	void showdetails() throws PassangerNotFoundException, NoProperDataException {
		
		
		Flight fly = new Flight();
		fly.setArrival_time(LocalTime.now());
		fly.setDate(LocalDate.of(2022, Month.JANUARY, 12));
		fly.setDepart_time(LocalTime.NOON);
		fly.setDestiny("Tumkur");
		fly.setFlightId("ABUS1");
		fly.setFlightname("AIRBUS");
		fly.setSource("Bengaluru");
		Passenger psngr = new Passenger();
		psngr.setAge(30);
		psngr.setEticket("ASLk");
		psngr.setFirstname("Akshay");
		psngr.setFlight(fly);
		psngr.setGender("male");
		psngr.setLastname("kumar");
		psngr.setPassengerid(4);
		
		when(passengerRepository.findById(any())).thenReturn(Optional.of(psngr));
		Passenger pass = bookingService.travellingDetails("ASLk");

		assertNotNull(pass);
		assertThat(pass.getAge()).isEqualTo(30);
	}
	
	
}




//public Passenger bookingTicket(String flightname, Passenger passenger)throws NoProperDataException;
//
//public Object travellingDetails(String token)throws NoProperDataException;
//
//public List<Passenger> getAllPassengers()throws PassangerNotFoundException;
//
//public Passenger getPassengerById(int id)throws PassangerNotFoundException;
