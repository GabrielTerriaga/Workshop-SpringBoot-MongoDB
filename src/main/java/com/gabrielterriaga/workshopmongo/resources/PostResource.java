package com.gabrielterriaga.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielterriaga.workshopmongo.domain.Post;
import com.gabrielterriaga.workshopmongo.resources.util.URL;
import com.gabrielterriaga.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired //instanciar objeto, injecao de dependencia auto do Spring
	private PostService service;
	
	//ENDPOINT FIND BY ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findId(@PathVariable String id){ //para dizer que esse id precisa ser igual o id recebido na url
	
		Post obj = service.findById(id);
	
		return ResponseEntity.ok().body(obj); //obj para comunicar com banco atraves do padrao DTO
	}
	
	//ENDPOINT FIND BY TITLE
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
	
		text = URL.decodeParam(text); //decodificar o string da url
		List<Post> list = service.findByTitle(text);
	
		return ResponseEntity.ok().body(list); //obj para comunicar com banco atraves do padrao DTO
	}
}
