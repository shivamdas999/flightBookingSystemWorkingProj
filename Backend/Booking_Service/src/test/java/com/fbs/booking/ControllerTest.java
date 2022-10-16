//package com.fbs.booking;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.Month;
//import java.util.List;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//
//import com.fbs.booking.controller.BookingController;
//import com.fbs.booking.exception.FlightNotFoundException;
//import com.fbs.booking.exception.NoProperDataException;
//import com.fbs.booking.exception.PassangerNotFoundException;
//import com.fbs.booking.model.Flight;
//import com.fbs.booking.model.Passenger;
//import com.fbs.booking.repository.FlightRepository;
//import com.fbs.booking.repository.PassengerRepository;
//import com.fbs.booking.service.BookingService;
//
//public class ControllerTest {
//
//	
//	
//	
//	@InjectMocks
//	private BookingController bookingController;
//
//	@Mock
//	private PassengerRepository passengerRepository;
//	
//	@InjectMocks
//	private BookingService bookingService;
//
//	@Mock
//	private FlightRepository flightRepository;
//
//	@Test
//	@DisplayName("Getting All Passengers")
//	public void allpassengers() throws NoProperDataException, NotFoundException, PassangerNotFoundException {
//		Flight fly = new Flight();
//		fly.setArrival_time(LocalTime.now());
//		fly.setDate(LocalDate.of(2022, Month.JANUARY, 12));
//		fly.setDepart_time(LocalTime.NOON);
//		fly.setDestiny("Tumkur");
//		fly.setFlightId("ABUS1");
//		fly.setFlightname("AIRBUS");
//		fly.setSource("Bengaluru");
//
//		Passenger psngr = new Passenger();
//		psngr.setAge(30);
//		psngr.setEticket("ASD555555Lk");
//		psngr.setFirstname("Akshay");
//		psngr.setFlight(fly);
//		psngr.setGender("male");
//		psngr.setLastname("kumar");
//		psngr.setPassengerid(1);
//		
//	
//		when(passengerRepository.save(any(Passenger.class))).thenReturn(psngr);
//		List<Passenger> passangers = bookingService.getAllPassengers();
//		ResponseEntity<?> alltraveller = bookingController.getAllTravellers();
//	
//		assertNotNull(alltraveller);
//
//	}
//	
//	@Test
//	@DisplayName("Getting passengers by id")
//	public void gettingbyid() throws Exception {
//		Flight fly = new Flight();
//		fly.setArrival_time(LocalTime.now());
//		fly.setDate(LocalDate.of(2022, Month.JANUARY, 12));
//		fly.setDepart_time(LocalTime.NOON);
//		fly.setDestiny("Tumkur");
//		fly.setFlightId("ABUS1");
//		fly.setFlightname("AIRBUS");
//		fly.setSource("Bengaluru");
//
//		Passenger psngr = new Passenger();
//		psngr.setAge(30);
//		psngr.setEticket("ASD555555Lk");
//		psngr.setFirstname("Akshay");
//		psngr.setFlight(fly);
//		psngr.setGender("male");
//		psngr.setLastname("kumar");
//		psngr.setPassengerid(1);
//		
//
//		when(passengerRepository.save(any(Passenger.class))).thenReturn(psngr);
//		List<Passenger> passangers = bookingService.getAllPassengers();
//		ResponseEntity<?> alltraveller = bookingController.getPassengerById(0);
//
//		assertNotNull(alltraveller);
//
//	}
//	@Test
//	@DisplayName("Adding Passengers")
//	public void booking() throws NoProperDataException, NotFoundException, FlightNotFoundException, PassangerNotFoundException, MethodArgumentNotValidException {
//		Flight fly = new Flight();
//		fly.setArrival_time(LocalTime.now());
//		fly.setDate(LocalDate.of(2022, Month.JANUARY, 12));
//		fly.setDepart_time(LocalTime.NOON);
//		fly.setDestiny("Tumkur");
//		fly.setFlightId("ABUS1");
//		fly.setFlightname("AIRBUS");
//		fly.setSource("Bengaluru");
//
//		Passenger psngr = new Passenger();
//		psngr.setAge(30);
//		psngr.setEticket("ASD555555Lk");
//		psngr.setFirstname("Akshay");
//		psngr.setFlight(fly);
//		psngr.setGender("male");
//		psngr.setLastname("kumar");
//		psngr.setPassengerid(1);
//		
//		
//		when(passengerRepository.save(any(Passenger.class))).thenReturn(psngr);
//		List<Passenger> passangers = bookingService.getAllPassengers();
//		ResponseEntity<?> alltraveller = bookingController.bookingTicket(null, psngr);
//
//		assertNotNull(alltraveller);
//
//	}
//	@Test
//	@DisplayName("Getting All Passengers")
//	public void travellingdetails() throws NoProperDataException, NotFoundException, PassangerNotFoundException {
//		Flight fly = new Flight();
//		fly.setArrival_time(LocalTime.now());
//		fly.setDate(LocalDate.of(2022, Month.JANUARY, 12));
//		fly.setDepart_time(LocalTime.NOON);
//		fly.setDestiny("Tumkur");
//		fly.setFlightId("ABUS1");
//		fly.setFlightname("AIRBUS");
//		fly.setSource("Bengaluru");
//
//		Passenger psngr = new Passenger();
//		psngr.setAge(30);
//		psngr.setEticket("ASD555555Lk");
//		psngr.setFirstname("Akshay");
//		psngr.setFlight(fly);
//		psngr.setGender("male");
//		psngr.setLastname("kumar");
//		psngr.setPassengerid(1);
//		
//
//		when(passengerRepository.save(any(Passenger.class))).thenReturn(psngr);
//		List<Passenger> passangers = bookingService.getAllPassengers();
//		ResponseEntity<?> alltraveller = bookingController.travellingDetails(null);
//
//		assertNotNull(alltraveller);
//
//	}
//	
//
//}
