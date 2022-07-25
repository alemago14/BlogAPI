package com.api.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
//indicamos el nombre de la tabla y tambien que no puede existir 2 publicaciones con el mismo titulo
@Table(name="publicaciones",uniqueConstraints= {@UniqueConstraint(columnNames= {"titulo"})})
public class Publicacion {

	//atributos
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="titulo",nullable=false)//indicamos el nombre de la columna en la bd tambien definimos que no sean nulas
	private String titulo;
	@Column(name="descripcion",nullable=false)
	private String descripcion;
	@Column(name="contenido",nullable=false)
	private String contenido;
	
	@JsonBackReference//Anotacion para que tambien nosmuestra los comentarios junto a las publicacion sin errores
	@OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)//orphan removal es cuando eliminemos un valor de la bd se eliminaran todos los datos relacionados a ese objeto
	private Set<Comentario> comentarios = new HashSet<>();
	
	//constructor vacio x defecto
	public Publicacion() {
	}
	
	//constructor con los atributos
	public Publicacion(Long id, String titulo, String descripcion, String contenido) {
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
