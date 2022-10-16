package com.fbs.authentication.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.fbs.authentication.models.ERole;
import com.fbs.authentication.models.Role;
@EnableMongoRepositories
public interface RoleRepository extends MongoRepository<Role, Integer>{
	Optional<Role> findByName(ERole name);
}
