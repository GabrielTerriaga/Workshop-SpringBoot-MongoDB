package com.gabrielterriaga.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielterriaga.workshopmongo.domain.User;
import com.gabrielterriaga.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired //instanciar objeto, injecao de dependencia auto do Spring
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){	
		
		List<User> list = service.findAdll(); //findAll dentro do user service vai trazer essa informacao
		
		return ResponseEntity.ok().body(list);
	}
}
