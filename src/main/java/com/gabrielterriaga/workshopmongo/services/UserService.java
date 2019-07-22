package com.gabrielterriaga.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielterriaga.workshopmongo.domain.User;
import com.gabrielterriaga.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired //instanciar objeto, injecao de dependencia auto do Spring
	private UserRepository repo;
	
	public List<User> findAdll(){
		return repo.findAll(); //findAll retorna todos os obj do tipo User
	}
}
