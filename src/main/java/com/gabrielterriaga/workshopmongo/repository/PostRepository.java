package com.gabrielterriaga.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielterriaga.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

		
}