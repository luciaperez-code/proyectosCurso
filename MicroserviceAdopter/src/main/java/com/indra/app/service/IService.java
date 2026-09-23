package com.indra.app.service;

import java.util.List;

import com.indra.app.entities.Adopter;

public interface IService {
	
	//1. Registrar un adoptante.
	boolean insert(Adopter a);
	
	//2. Consultar todos los adoptantes.
	List<Adopter> findAll();
	
	//3. Buscar adoptante por id.
	Adopter findById(long id);

	//4. Buscar adoptante por email.
	Adopter findByEmail(String email);

	//5. Buscar adoptantes por apellido.
	List<Adopter> findByLastName(String lastName);
	
	//6. Actualizar información de un adoptante.
	boolean update(Adopter a);
	
	//7. Eliminar un adoptante por id.
	boolean deleteById(long id);
	
		


}
