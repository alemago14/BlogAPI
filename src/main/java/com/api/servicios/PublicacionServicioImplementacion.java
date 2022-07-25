package com.api.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.dto.PublicacionDTO;
import com.api.dto.PublicacionRespuesta;
import com.api.entidades.Publicacion;
import com.api.exepciones.ResourceNotFound;
import com.api.repositorios.PublicacionRepositorio;

@Service
public class PublicacionServicioImplementacion implements PublicacionServicio{

	@Autowired
	private ModelMapper modelMapper;
	
	//metodo de la interface publicacion servicio sobreescrito
	//metodo para guardar una nueva publicacion en la bd y enviar de respuesta la publicacion dto
	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		//Convertimos DTO a entidad
		Publicacion publicacion = new Publicacion();
		
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		Publicacion nuevaPubli = publicacionRepo.save(publicacion);
		
		//Convertir de entidad a DTO
		PublicacionDTO publiRespuesta = new PublicacionDTO();
		
		publiRespuesta.setId(nuevaPubli.getId());
		publiRespuesta.setTitulo(nuevaPubli.getTitulo());
		publiRespuesta.setDescripcion(nuevaPubli.getDescripcion());
		publiRespuesta.setContenido(nuevaPubli.getContenido());
		
		
		return publiRespuesta;
	}
	
	//atributos
	@Autowired
	private PublicacionRepositorio publicacionRepo;

	//metodo para mostrar todas las publicaciones
	public PublicacionRespuesta listarPublicaciones(int numPagina, int medidaDePagina, String ordenarPor, String sortDir){
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		
		Pageable pageable = PageRequest.of(numPagina, medidaDePagina, sort);
		Page<Publicacion> datos = publicacionRepo.findAll(pageable);
		
		List<Publicacion> publicaciones = datos.getContent();
		List<PublicacionDTO> publicacionesDtos = publicaciones.stream().map(publicacion -> convertirADTO(publicacion)).collect(Collectors.toList());
		
		PublicacionRespuesta publiRespuesta = new PublicacionRespuesta();
		publiRespuesta.setContenido(publicacionesDtos);
		publiRespuesta.setNumPagina(datos.getNumber());
		publiRespuesta.setMedidaDePagina(datos.getSize());
		publiRespuesta.setTotalElementos(datos.getTotalElements());
		publiRespuesta.setTotalPaginas(datos.getTotalPages());
		publiRespuesta.setUltima(datos.isLast());
		
		return publiRespuesta;
		
		
		
	}
	
	public PublicacionDTO buscarxId(Long id) {
		Publicacion publicacion = publicacionRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Publicacion", "Id", id));
		return convertirADTO(publicacion);
	}
	
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDto, Long id) {
		Publicacion publicacion = publicacionRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Publicacion", "Id", id));
		
		publicacion.setTitulo(publicacionDto.getTitulo());
		publicacion.setDescripcion(publicacionDto.getDescripcion());
		publicacion.setContenido(publicacionDto.getContenido());
		
		Publicacion publicacionActualizada = publicacionRepo.save(publicacion);
		return convertirADTO(publicacionActualizada);
	}
	
	//metodo para covertir una entidad a dto
	public PublicacionDTO convertirADTO (Publicacion publicacion) {
		PublicacionDTO publicacionDTO = modelMapper.map(publicacion, PublicacionDTO.class);
		
		return publicacionDTO;
	}
	
	//metodo para convertir un dto a entidad
	public Publicacion convertirAEntidad(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = modelMapper.map(publicacionDTO, Publicacion.class);
		
		return publicacion;
	}
	
	//metodo para eliminar una publicacion
	public PublicacionDTO eliminarPublicacion(Long id) {
		Publicacion publicacion = publicacionRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Publicacion", "Id", id));
		
		publicacionRepo.delete(publicacion);
		
		return convertirADTO(publicacion);
	}
}
