package com.api.exepciones;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.dto.ErrorDetalles;

@ControllerAdvice //Anotacion que le permite a la clase manejar todas las excepciones de la aplicacion
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorDetalles> manejadorDeExcepcionRecusoNoEncontrado(ResourceNotFound exception,WebRequest webRequest){
		ErrorDetalles error = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetalles> manejadorDeExcepciones(ResourceNotFound exception,WebRequest webRequest){
		ErrorDetalles error = new ErrorDetalles(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String nombreCampo = ((FieldError)error).getField();
			String mensaje = error.getDefaultMessage();
			
			errores.put(nombreCampo, mensaje);
		});
		
		return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
	}
		
}
