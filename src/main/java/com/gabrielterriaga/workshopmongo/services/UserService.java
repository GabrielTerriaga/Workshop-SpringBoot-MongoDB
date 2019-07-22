package com.gabrielterriaga.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielterriaga.workshopmongo.domain.User;
import com.gabrielterriaga.workshopmongo.dto.UserDTO;
import com.gabrielterriaga.workshopmongo.repository.UserRepository;
import com.gabrielterriaga.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	//Esta classe faz uma dependencia com o banco de dados (UserRepository)
	
	@Autowired //instanciar objeto, injecao de dependencia auto do Spring
	private UserRepository repo;
	
	public List<User> findAdll(){
		return repo.findAll(); //findAll retorna todos os obj do tipo User
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
