package com.indra.app.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.indra.app.entities.Adopter;

//Sin Eureka
//@FeignClient(name="microserviceadopter", url="http://localhost:9092")

//Con Eureka
@FeignClient(name="microserviceadopter")
public interface IAdopterFeign {
	
	@GetMapping("/adopters/id")
	Adopter findById(@RequestParam("id") Long id);
	
	@GetMapping("/adopters/email")
	Adopter findByEmail(@RequestParam("email") String email);
	
	
}
