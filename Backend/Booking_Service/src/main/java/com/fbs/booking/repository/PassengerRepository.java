package com.fbs.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fbs.booking.model.Passenger;

@Repository
public interface PassengerRepository extends MongoRepository<Passenger, Integer>{
	Passenger findByEticket(String ticket);
	Passenger findByPassengerid(int id);
}
