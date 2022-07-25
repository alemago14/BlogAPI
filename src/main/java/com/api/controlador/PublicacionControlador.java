package com.api.controlador;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.PublicacionDTO;
import com.api.dto.PublicacionRespuesta;
import com.api.servicios.PublicacionServicioImplementacion;
import com.api.utilidades.AppConstantes;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {

	//atributos - llamada de servicio
	@Autowired
	private PublicacionServicioImplementacion publicacionServicio;
	
	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO){
		return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO),HttpStatus.CREATED);
	}
	
	@GetMapping
	//con paginacion y organizacion o (pagination and sorting)
	public PublicacionRespuesta listar(@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_PAGINA_DEFECTO, required = false) int numeroDePagina,
			@RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaDePagina,
			@RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir){
		return publicacionServicio.listarPublicaciones(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> publicacionPorId(@PathVariable (name="id") Long id){
		return ResponseEntity.ok(publicacionServicio.buscarxId(id));
	}
	
	//putmapping para actualizar
	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDto, @PathVariable (name="id") Long id){
		PublicacionDTO publiResp = publicacionServicio.actualizarPublicacion(publicacionDto, id);
		return new ResponseEntity<>(publiResp,HttpStatus.OK);
	}
	
	//eliminar publicacion
	@DeleteMapping("/{id}")
	public ResponseEntity<PublicacionDTO> eliminarPublicacion(@PathVariable Long id){
		PublicacionDTO publiResp = publicacionServicio.eliminarPublicacion(id);
		return new ResponseEntity<>(publiResp, HttpStatus.OK);
	}
}
