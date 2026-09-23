package com.indra.app.services;

import java.util.List;

import com.indra.app.entities.AdoptionRequest;
import com.indra.app.entities.AdoptionRequestStatus;

public interface IService {

	boolean insert(Long userId, Long petId);
	List<AdoptionRequest> findAll();
	List<AdoptionRequest> findByEmail(String email);
	boolean deleteById(long id);
	boolean updateAdoptionRequestStatus(long id, AdoptionRequestStatus status);
		
}
