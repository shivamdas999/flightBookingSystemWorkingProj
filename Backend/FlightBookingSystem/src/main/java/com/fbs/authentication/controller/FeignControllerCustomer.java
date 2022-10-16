package com.fbs.authentication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fbs.authentication.exception.CustomerNotFoundException;
import com.fbs.authentication.exception.NoProperDataException;
import com.fbs.authentication.models.Customer;
import com.fbs.authentication.services.SequenceGeneratorService;
import com.fbs.authentication.util.FiegnClientUtilCustomer;

@RestController
@RequestMapping("/api/v2")
public class FeignControllerCustomer {

	@Autowired
	private FiegnClientUtilCustomer feigncustomer;

	@Autowired
	private SequenceGeneratorService service;

	@GetMapping("/allcustomers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getAllCustomer(@RequestHeader("Authorization") String token)
			throws CustomerNotFoundException {

		return feigncustomer.getAllCustomer(token);

	}

	@GetMapping("/customers/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Customer> getCustomerById(@Valid @RequestHeader("Authorization") String token,
			@PathVariable Integer id) throws CustomerNotFoundException {
		return feigncustomer.getCustomerById(token, id);
	}

	@PostMapping("/addCustomers")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestHeader("Authorization") String token,
			@RequestBody Customer customer) throws NoProperDataException {
		customer.setCust_id(service.getSequenceNumberForCustomer(Customer.SEQUENCE_NAME));
		return feigncustomer.addCustomer(token, customer);
	}

	@DeleteMapping(path = "/customers/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCustomer(@Valid @RequestHeader("Authorization") String token,
			@PathVariable int id) throws CustomerNotFoundException {
		return feigncustomer.deleteCustomer(token, id);
	}

}
