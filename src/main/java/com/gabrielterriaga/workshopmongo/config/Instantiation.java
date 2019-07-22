package com.gabrielterriaga.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielterriaga.workshopmongo.domain.User;
import com.gabrielterriaga.workshopmongo.repository.UserRepository;

@Configuration //para o spring entender que é uma configuracao
public class Instantiation implements CommandLineRunner {

	@Autowired //instanciar obOjeto, injecao de dependencia auto do Spring
	private UserRepository userRepository;
	
	@Override //esse metodo esta excluindo tudo e instanciando esses objs, por isso o metodo POST no servico nao esta salvando apos o Relaunch da app
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}

}
