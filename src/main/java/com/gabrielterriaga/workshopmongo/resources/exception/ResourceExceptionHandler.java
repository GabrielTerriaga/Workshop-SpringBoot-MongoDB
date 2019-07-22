package com.gabrielterriaga.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gabrielterriaga.workshopmongo.service.exception.ObjectNotFoundException;

@ControllerAdvice //vai indicar que essa classe vai tratar possiveis erros na minha app
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) //identifica que ao ocorrer a excecao é para fazer esse tratamento
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
