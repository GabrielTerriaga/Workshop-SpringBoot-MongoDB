package com.gabrielterriaga.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielterriaga.workshopmongo.domain.Post;
import com.gabrielterriaga.workshopmongo.repository.PostRepository;
import com.gabrielterriaga.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	//Esta classe faz uma dependencia com o banco de dados (UserRepository)
	
	@Autowired //instanciar objeto, injecao de dependencia auto do Spring
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}
}
