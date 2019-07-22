package com.gabrielterriaga.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielterriaga.workshopmongo.domain.User;
import com.gabrielterriaga.workshopmongo.dto.UserDTO;
import com.gabrielterriaga.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired //instanciar objeto, injecao de dependencia auto do Spring
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){	
		
		List<User> list = service.findAdll(); //findAll dentro do user service vai trazer essa informacao
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findId(@PathVariable String id){ //para dizer que esse id precisa ser igual o id recebido na url
	
		User obj = service.findById(id);
	
		return ResponseEntity.ok().body(new UserDTO(obj)); //obj para comunicar com banco atraves do padrao DTO
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){ //para dizer que esse id precisa ser igual o id recebido na url
	
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj); //inserir no DB
	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //serve para retornar um cabecalho com o id
		return ResponseEntity.created(uri).build();
	}
}
