package com.gabrielterriaga.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielterriaga.workshopmongo.domain.User;
import com.gabrielterriaga.workshopmongo.repository.UserRepository;
import com.gabrielterriaga.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired //instanciar objeto, injecao de dependencia auto do Spring
	private UserRepository repo;
	
	public List<User> findAdll(){
		return repo.findAll(); //findAll retorna todos os obj do tipo User
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
