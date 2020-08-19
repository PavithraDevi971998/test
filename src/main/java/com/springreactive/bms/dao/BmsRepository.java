package com.springreactive.bms.dao;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.springreactive.bms.domain.Register;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BmsRepository extends ReactiveMongoRepository<Register,Integer> {

	

	Mono<Register> findByUsernameAndPassword(String username, String password);

	Mono<ResponseEntity<Register>> findByusername(String username);

	Mono<Register> findByEmail(String email);

	Flux<Register> findByUsername(String string);

	

	

	
}
