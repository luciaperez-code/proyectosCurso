package com.indra.app.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.indra.app.entities.Adopter;

@FeignClient(name="microservicepet", url="http://localhost:9092")
public interface IAdopterFeign {
	
	@GetMapping("/adopter/id")
	Adopter findById(@RequestParam("id") Long id);
	
	@GetMapping("/adopter/email")
	Adopter findByEmail(@RequestParam("email") String email);
	
	
}
