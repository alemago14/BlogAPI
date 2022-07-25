package com.api.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.api.entidades.Comentario;

public class PublicacionDTO {

	//atributos
	
	private Long id;
	
	@NotEmpty
	@Size(min=2,message="El titulo debe tener como minimo 2 caracteres.")
	private String titulo;
	@NotEmpty
	@Size(min=10,message="La descripcion debe tener como minimo 10 caracteres.")
	private String descripcion;
	@NotEmpty
	private String contenido;
	private Set<Comentario> comentarios;
	
	
	//constructores
	public PublicacionDTO() {
	}
	
	public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}

	
	//getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
}
