package com.api.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ComentarioDto;
import com.api.entidades.Comentario;
import com.api.servicios.ComentarioServicio;

@RestController
@RequestMapping("/api")
public class ComentarioControlador {

	@Autowired
	private ComentarioServicio comentarioServicio;
	
	@GetMapping("/publicaciones/{publicacionId}/comentarios")
	public List<ComentarioDto> listarComentarioPorPublicacion(@PathVariable (value="publicacionId") Long id){
		return comentarioServicio.listarComentarios(id);
	}
	
	@PostMapping("/publicaciones/{publicacionId}/comentarios")
	public ResponseEntity<ComentarioDto> guardarComentario(@PathVariable (value="publicacionId") Long id,@Valid  @RequestBody ComentarioDto comentarioDto){
		return new ResponseEntity<>(comentarioServicio.crearComentario(comentarioDto, id),HttpStatus.CREATED);
	}
}
