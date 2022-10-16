package com.fbs.authentication.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer_details")
public class Customer {
   
	public static final String SEQUENCE_NAME = "customer_sequence";
	@Id
	private int cust_id;
	
	
	@NotBlank(message="username is required")
	@Size(max = 40)
	private String username;
	
	@NotBlank(message="email is required")
	@Size(max = 40)
	@Email(message="invalid email")
	private String email;
	
	@NotBlank(message="phone number is required")
	@Size(max=10)
	@Size(min=10)
	private String contact_No;
	
	@NotBlank(message="address is required")
	private String address;
}
