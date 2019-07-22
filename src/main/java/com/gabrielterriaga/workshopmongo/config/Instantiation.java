package com.gabrielterriaga.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielterriaga.workshopmongo.domain.Post;
import com.gabrielterriaga.workshopmongo.domain.User;
import com.gabrielterriaga.workshopmongo.dto.AuthorDTO;
import com.gabrielterriaga.workshopmongo.repository.PostRepository;
import com.gabrielterriaga.workshopmongo.repository.UserRepository;

@Configuration //para o spring entender que é uma configuracao
public class Instantiation implements CommandLineRunner {

	@Autowired //instanciar obOjeto, injecao de dependencia auto do Spring
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override //esse metodo esta excluindo tudo e instanciando esses objs, por isso o metodo POST no servico nao esta salvando apos o Relaunch da app
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));//LONDRES
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
