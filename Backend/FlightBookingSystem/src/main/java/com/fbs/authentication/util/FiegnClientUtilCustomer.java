package com.fbs.authentication.util;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fbs.authentication.models.Customer;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(value = "Customer-Service", url = "http://localhost:9091/api/v2")
public interface FiegnClientUtilCustomer {

	@GetMapping("/allcustomers")
	public ResponseEntity<List<Customer>> getAllCustomer(@RequestHeader("Authorization") String token);

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@RequestHeader("Authorization") String token, @PathVariable int id);

	@PostMapping("/addCustomers")
	public ResponseEntity<Customer> addCustomer(@RequestHeader("Authorization") String token,@RequestBody Customer customer);

	@DeleteMapping(path = "/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String token, @PathVariable int id);

}
