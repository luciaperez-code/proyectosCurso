package com.indra.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.indra.app.entities.Adopter;

public interface IAdopterDAO extends CrudRepository<Adopter, Long>{
	
	@Query("SELECT a FROM Adopter a WHERE a.email = :email")
	Optional<Adopter> findByEmail(@Param("email") String email);
	
	@Query("SELECT a FROM Adopter a WHERE a.lastName = :lastName")
	Optional<List<Adopter>> findByLastName(@Param("lastName") String lastName);
	
}
