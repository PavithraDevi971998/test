package com.springreactive.bms;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springreactive.bms.dao.BmsRepository;

import com.springreactive.bms.domain.Register;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@RunWith(SpringRunner.class)
public class BmsTest {
	
	@Autowired
	BmsRepository bmsRepository;

    List<Register>  itemList = Arrays.asList(new Register(101,"pavi","abc","erode","tamil nadu","india","pavi@gmail.com",123456,98765,"12/03/1979","current",4325678),
            new Register(102,"raj","raj","cbe","tamil nadu","india","raj@gmail.com",987456,123465,"09/03/1999","savings",4325678),
            new Register(103,"sam","sam","namakkal","tamil nadu","india","sam@gmail.com",111456,77765,"14/09/2009","current",995678));

           
    @Before
    public void setUp(){

    	bmsRepository.deleteAll()
                .thenMany(Flux.fromIterable(itemList))
                .flatMap(bmsRepository::save)
                .doOnNext((item -> {
                    System.out.println("Inserted Item is :" + item);
                }))
                .blockLast();

    }
	
	@Test
    public void getAllItems() {

        StepVerifier.create(bmsRepository.findAll()) // 2
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();
    }
	
	
	  @Test
	  public void saveItem(){
	  
	  Register register = new Register(108,"kumar","kumar","salem","tamil nadu","india","kumar@gmail.com",123456,98765,"21/03/1879","current",4325678);
	  Mono<Register> savedItem =bmsRepository.save(register);
	  StepVerifier.create(savedItem.log("saveItem : "))
	  .expectSubscription()
	  .expectNextMatches(customer -> (customer.getUsername().equals("kumar") &&
	  customer.getState().equals("tamil nadu"))) 
	  .verifyComplete();
	  
	  }
	  @Test
	    public void updateItem(){

	        String address = "thanjavur";
	        Flux<Register> updatedItem = bmsRepository.findByUsername("pavi")
	                .map(item -> {
	                      item.setAddress(address); 
	                      return item;
	                })
	                .flatMap((item) -> {
	                    return bmsRepository.save(item); //saving the item with the new price
	                });

	        StepVerifier.create(updatedItem)
	                .expectSubscription()
	                .expectNextMatches(item -> item.getAddress().equals("thanjavur"))
	                .verifyComplete();


	    }
	 
}
