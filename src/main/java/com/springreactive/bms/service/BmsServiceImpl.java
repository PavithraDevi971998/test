package com.springreactive.bms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springreactive.bms.dao.BmsRepository;
import com.springreactive.bms.domain.Register;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BmsServiceImpl implements BmsService {
	
	@Autowired
	BmsRepository bmsRepository;

	@Override
	public Mono<Register> save(Register register) {
		// TODO Auto-generated method stub
		return bmsRepository.save(register);
	}

	@Override
	public Mono<Register> findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return bmsRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public Mono<Register> findById(int id) {
		// TODO Auto-generated method stub
		return bmsRepository.findById(id);
	}

	@Override
	public Flux<Register> findAll() {
		// TODO Auto-generated method stub
		return bmsRepository.findAll();
	}

	@Override
	public Mono<Register> findByEmail(String email) {
		// TODO Auto-generated method stub
		return bmsRepository.findByEmail(email);
	}

	
	
	

}
