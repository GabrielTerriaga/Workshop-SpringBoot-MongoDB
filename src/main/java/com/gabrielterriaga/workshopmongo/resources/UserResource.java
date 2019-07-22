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

import com.gabrielterriaga.workshopmongo.domain.Post;
import com.gabrielterriaga.workshopmongo.domain.User;
import com.gabrielterriaga.workshopmongo.dto.UserDTO;
import com.gabrielterriaga.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired //instanciar objeto, injecao de dependencia auto do Spring
	private UserService service;
	
	//ENDPOINT FIND ALL
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){	
		
		List<User> list = service.findAdll(); //findAll dentro do user service vai trazer essa informacao
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	//ENDPOINT FIND BY ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findId(@PathVariable String id){ //para dizer que esse id precisa ser igual o id recebido na url
	
		User obj = service.findById(id);
	
		return ResponseEntity.ok().body(new UserDTO(obj)); //obj para comunicar com banco atraves do padrao DTO
	}
	
	//ENDPOINT DELETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){ //para dizer que esse id precisa ser igual o id recebido na url
	
		service.delete(id);
	
		return ResponseEntity.noContent().build(); //se retornar vazio apresenta um ERROR 404
	}
	
	//ENDPOINT POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){ //requisitando um body json para usar o metodo POST no mongo
	
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj); //inserir no DB
	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //serve para retornar um cabecalho com o id
		return ResponseEntity.created(uri).build();
	}
	
	//ENDPOINT PUT
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){ //Requisitando o body da classe DTO que esta fazendo toda o tipo de transferencia para o DB, um parametro do tipo Id
		
		User obj = service.fromDTO(objDto);
		obj.setId(id);//id vindo por parametro atraves do endopoint do Spring
		obj = service.update(obj); //inserir no DB
		
		return ResponseEntity.noContent().build(); //se retornar vazio apresenta um ERROR 404
	}
		
	//ENDPOINT RETORNAR LISTA DE POSTS
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){ //para dizer que esse id precisa ser igual o id recebido na url
		
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj.getPosts()); //obj para comunicar com banco atraves do padrao DTO
	}
}
