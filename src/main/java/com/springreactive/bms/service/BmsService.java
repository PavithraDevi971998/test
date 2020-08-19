package com.springreactive.bms.service;

import com.springreactive.bms.domain.Register;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BmsService {

	Mono<Register> save(Register register);

	Mono<Register> findByUsernameAndPassword(String username, String password);

	Mono<Register> findById(int id);

	Flux<Register> findAll();

	Mono<Register> findByEmail(String email);

}
