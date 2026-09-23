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

import com.indra.app.entities.AdoptionStatus;
import com.indra.app.entities.Pet;
import com.indra.app.exception.PetNotFoundException;
import com.indra.app.service.IService;

@RestController //JSON
@RequestMapping("/pets") //http://ip:port/pets
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
	public ResponseEntity<String> insert(@RequestBody Pet p){
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
	public ResponseEntity<List<Pet>> findAll(){
		try {
			return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
		}catch(PetNotFoundException pnfex) {
			LOGGER.warn("FindAll {}", pnfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}catch (Exception ex) {
			LOGGER.error("FindAll {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}

	
	//Búsqueda por ID
	@GetMapping("/id")
	public ResponseEntity<Pet> findByID(@RequestParam("id") long id){
		try {
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		}catch(PetNotFoundException pnfex) {
			LOGGER.warn("findByID {}", pnfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}catch (Exception ex) {
			LOGGER.error("findByID {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
	
	//Búsqueda por especie
	@GetMapping("/specie")
	public ResponseEntity<List<Pet>> findBySpecie(@RequestParam("specie") String specie){
		try {
			return new ResponseEntity<>(service.findBySpecie(specie), HttpStatus.OK);
		}catch(PetNotFoundException pnfex) {
			LOGGER.warn("findBySpecie {}", pnfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}catch (Exception ex) {
			LOGGER.error("findBySpecie {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
	
	//Actualización
	@PutMapping
	public ResponseEntity<Boolean> updateById(@RequestParam("p") Pet p){
		try {
			return new ResponseEntity<>(service.update(p), HttpStatus.OK);
		}catch(PetNotFoundException pnfex) {
			LOGGER.warn("updateById {}", pnfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}catch (Exception ex) {
			LOGGER.error("updateById {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
	
	//Actualización estado de adopción
	@PutMapping("/status")
	public ResponseEntity<Boolean> updateAdoptionStatus(
			@RequestParam("") Long id,
			@RequestParam("status") AdoptionStatus status
			){
		
		try {
			return new ResponseEntity<>(service.updateAdoptionStatus(id, status), HttpStatus.OK);
		}catch(PetNotFoundException pnfex) {
			LOGGER.warn("updateAdoptionStatus {}", pnfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}catch (Exception ex) {
			LOGGER.error("updateAdoptionStatus {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
	
	
	//Elminación de mascota
	@DeleteMapping
	public ResponseEntity<Boolean> deleteById(@RequestParam("id") Long id){
		
		try {
			return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
		}catch(PetNotFoundException pnfex) {
			LOGGER.warn("deleteById {}", pnfex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}catch (Exception ex) {
			LOGGER.error("deleteById {}", ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
}
