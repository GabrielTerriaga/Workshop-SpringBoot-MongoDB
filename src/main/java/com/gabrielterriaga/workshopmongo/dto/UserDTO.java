package com.gabrielterriaga.workshopmongo.dto;

import java.io.Serializable;

import com.gabrielterriaga.workshopmongo.domain.User;

//DTO = Obj que facilita na hora de fazer alguma transferencia com o DB
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//body DTO
	private String id;
	private String name;
	private String email;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User obj) {
		//para instanciar um userDTO a partir do user
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
