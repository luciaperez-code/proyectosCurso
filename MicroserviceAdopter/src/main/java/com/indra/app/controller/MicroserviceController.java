package com.indra.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indra.app.entities.Adopter;
import com.indra.app.exception.AdopterNotFoundException;
import com.indra.app.service.IService;

@RestController //JSON
@RequestMapping("/adopters") //http://ip:port/pets
public class MicroserviceController {
	
	private IService service; //Inversión de dependencias, si cambio el service no tengo que modificar el contrller
	private static final Logger LOGGER = LoggerFactory.getLogger(MicroserviceController.class);
			
	//Dependency injection
	public MicroserviceController(IService service) {
		this.service = service;
	}
	
	/** Mapping methods
	 * POST -> insert
	 * GET -> query
	 * PUT -> update
	 * PATCH -> partial update
	 * DELETE -> delete
	 */
	
	//Insert
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Adopter p){
		try {
			if(service.insert(p)) {
				return new ResponseEntity<>("OK", HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
			}
		}catch(Exception ex) {
			LOGGER.error("Insert {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Búsqueda
	@GetMapping
	public ResponseEntity<List<Adopter>> findAll(){
		try {
			return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
		}catch(AdopterNotFoundException anfex) {
			LOGGER.warn("FindAll {}", anfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}catch (Exception ex) {
			LOGGER.error("FindAll {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}

	
	//Búsqueda por ID
	@GetMapping("/id")
	public ResponseEntity<Adopter> findByID(@RequestParam("id") long id){
		try {
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		}catch(AdopterNotFoundException anfex) {
			LOGGER.warn("findByID {}", anfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}catch (Exception ex) {
			LOGGER.error("findByID {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
	
	//Búsqueda por email
	@GetMapping("/email")
	public ResponseEntity<Adopter> findByEmail(@RequestParam("email") String email){
		try {
			return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
		}catch(AdopterNotFoundException anfex) {
			LOGGER.warn("findByEmail {}", anfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}catch (Exception ex) {
			LOGGER.error("findByEmail {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
	
	//Búsqueda por apellido
	@GetMapping("/lastName")
	public ResponseEntity<List<Adopter>> findByLastName(@RequestParam("lastName") String lastName){
		try {
			return new ResponseEntity<>(service.findByLastName(lastName), HttpStatus.OK);
		}catch(AdopterNotFoundException anfex) {
			LOGGER.warn("findByLastName {}", anfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}catch (Exception ex) {
			LOGGER.error("findByLastName {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
	
	//Actualización
	@PutMapping
	public ResponseEntity<Boolean> updateById(@RequestParam("adopter") Adopter adopter){
		try {
			return new ResponseEntity<>(service.update(adopter), HttpStatus.OK);
		}catch(AdopterNotFoundException anfex) {
			LOGGER.warn("updateById {}", anfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}catch (Exception ex) {
			LOGGER.error("updateById {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
		
	
	//Elminación de adoptante
	@DeleteMapping
	public ResponseEntity<Boolean> deleteById(@RequestParam("id") Long id){
		
		try {
			return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
		}catch(AdopterNotFoundException anfex) {
			LOGGER.warn("deleteById {}", anfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}catch (Exception ex) {
			LOGGER.error("deleteById {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
}
