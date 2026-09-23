package com.indra.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.indra.app.dao.IAdopterDAO;
import com.indra.app.entities.Adopter;
import com.indra.app.exception.AdopterNotFoundException;

@Service
public class ServiceImpl implements IService {

	private IAdopterDAO dao;
	
	public ServiceImpl (IAdopterDAO dao) {
		this.dao=dao;
	}
	
	@Override
	public boolean insert(Adopter p) {
		if(p.getId()==0) {
			return dao.save(p)!=null;
		}
		return false;
	}

	@Override
	public List<Adopter> findAll() {
		
        return Optional.of(dao.findAll())
                .map(t->(List<Adopter>)t)
                .filter(t->!t.isEmpty())
                .orElseThrow(()->new AdopterNotFoundException("empty result"));
	}
	
	@Override
	public Adopter findById(long id) {
		//Devuelve Optional, por eso se puede usar directamente
		return dao.findById(id)
				.orElseThrow(()-> new AdopterNotFoundException("Adopter doesn't exists"));
	}

	@Override
	public Adopter findByEmail(String email) {
		return dao.findByEmail(email)
				.orElseThrow(()-> new AdopterNotFoundException("email doesn't exist :("));
	}

	@Override
	public List<Adopter> findByLastName(String lastName) {
		return dao.findByLastName(lastName)
				.filter(t->!t.isEmpty())
				.orElseThrow(()-> new AdopterNotFoundException("lastName doesn't exist :("));
	}

	@Override
	public boolean update(Adopter p) {
		if(dao.existsById(p.getId())){
			return dao.save(p)!=null;
		}
		throw new AdopterNotFoundException("Adopter " + p.getId() + " doesn't exist, can't update");
	}

	@Override
	public boolean deleteById(long id) {
		
		if(dao.existsById(id)) {
			dao.deleteById(id);
			return true;
		}
		
		throw new AdopterNotFoundException("deleteById: Adopter with ID" + id + " doesn't exist");
	}
}
