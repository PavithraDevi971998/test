package com.springreactive.bms.controller;

import java.util.Date;
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springreactive.bms.domain.ExceptionResponse;
import com.springreactive.bms.domain.Register;
import com.springreactive.bms.exception.AlreadyExistsException;
import com.springreactive.bms.service.BmsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
public class RegisterController extends ResponseEntityExceptionHandler {
	
	@Autowired
	BmsService bmsService;
	
	@PostMapping("/bms")
	public Mono<Register> registerCustomer(@Valid @RequestBody Register register){
		
//		return bmsService.findByEmail(register.getEmail())
//				.flatMap(result->{
//					System.out.println(result);
//					if(result) {
//				System.out.println("hiii");
//						return bmsService.save(register);
//					}
//					else {
//						return Mono.error(new AlreadyExistsException());
//					}});		
		return bmsService.save(register);}
//		Mono<Register> result = bmsService.findByEmail(register.getEmail());
//		return result.map(user->{
//			return  bmsService.save(register);
//		}).Mono.error(new AlreadyExistsException());
		
//		return bmsService.findByEmail(register.getEmail())
//				.flatMap(result->{
//					return  bmsService.save(register);
//					});
//				}
	
	@GetMapping(path="/bms/username/{username}/password/{password}")
	public Mono<String> logincheck(@PathVariable String username,@PathVariable String password ){
		
		 Mono<Register> result = bmsService.findByUsernameAndPassword(username,password);
		
				return result.map(user->{
					return "user logged in successfully";
				}).defaultIfEmpty("user not found");
		
	}
	@PostMapping(path="/bms/login")
	public Mono<String> logincheckdemo(@RequestBody Register register ){
		
		String username=register.getUsername();
		String password=register.getPassword();
		 Mono<Register> result = bmsService.findByUsernameAndPassword(username,password);
		
				return result.map(user->{
					return "user logged in successfully";
				}).defaultIfEmpty("user not found");
		
	}
	@PutMapping("/bms/{id}")
	public Mono<ResponseEntity<String>> updatedetails(@PathVariable int id,@RequestBody Register register){
		 return bmsService.findById(id)
	                .flatMap(currentItem -> {

	                    currentItem.setAddress(register.getAddress());
	                    currentItem.setEmail(register.getEmail());
	                    currentItem.setContactno(register.getContactno());
	                    return bmsService.save(currentItem);
	                })
	                .map(updatedItem -> new ResponseEntity<>("Details updated successfully", HttpStatus.OK)).log()
              .defaultIfEmpty(new ResponseEntity<>("update failed user not found",HttpStatus.NOT_FOUND)).log();
//	                .map(updatedItem -> new ResponseEntity<>(updatedItem, HttpStatus.OK))
//	                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND)).log();
	}
	@GetMapping("/customer/findall")
	public Flux<Register> customervalues()
	{	
		System.out.println("hitting");
		return bmsService.findAll();
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
}
