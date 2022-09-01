package com.api.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ComentarioDto;
import com.api.entidades.Comentario;
import com.api.entidades.Publicacion;
import com.api.exepciones.ResourceNotFound;
import com.api.repositorios.ComentarioRepositorio;
import com.api.repositorios.PublicacionRepositorio;



@Service
public class ComentarioServicio {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ComentarioRepositorio comentarioRepo;
	
	@Autowired
	private PublicacionRepositorio publicacionRepo;
	
	public List<ComentarioDto> listarComentarios(Long publicacionId) {
		List<Comentario> comentarios = comentarioRepo.findByPublicacionId(publicacionId);
		return comentarios.stream().map(comentario -> convertirADto(comentario)).collect(Collectors.toList());
	}
	
	//metodo para crear una publicacion recibimos comentario y el id de la publicacion
	public ComentarioDto crearComentario(ComentarioDto comentarioDto, Long publicacionID) {
		Publicacion publicacion = publicacionRepo.findById(publicacionID).orElseThrow(() -> new ResourceNotFound("Publicacion", "Id", publicacionID));
		Comentario comentario = convertirAEntidad(comentarioDto);
		
		comentario.setPublicacion(publicacion);
		
		comentarioRepo.save(comentario);
		comentarioDto = convertirADto(comentario);
		
		
		return comentarioDto;
	}
	
	//metodo para actualizar un comentario
	public ComentarioDto actualizar(ComentarioDto comentarioDto, Long id) {
		Comentario comentario = comentarioRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Comentario", "ID:", id));
		
		comentario.setNombre(comentarioDto.getNombre());
		comentario.setCuerpo(comentarioDto.getCuerpo());
		comentario.setNombre(comentarioDto.getCuerpo());
		
		Comentario comentarioActualizado = comentarioRepo.save(comentario);
		
		return convertirADto(comentarioActualizado);
	}
	
	//Eliminar un comentario
	public ComentarioDto eliminarComentario(Long id) {
		Comentario comentario = comentarioRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Comentario", "ID:", id));
		comentarioRepo.delete(comentario);
		return convertirADto(comentario);

	}
	
	
	//convertidor de entidad a dto
	public ComentarioDto convertirADto(Comentario comentario) {
		ComentarioDto comentarioDto = modelMapper.map(comentario, ComentarioDto.class);
		
		return comentarioDto;
	}
	
	//convertidor de dto a entidad
	public Comentario convertirAEntidad(ComentarioDto comentarioDto) {
		Comentario comentario = modelMapper.map(comentarioDto, Comentario.class);
		return comentario;
	}
	
	public void eliminarComentario(Long publicacionId, Long comentarioId) {
		Publicacion publicacion = publicacionRepo.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFound("Publicacion", "id", publicacionId));
		
		Comentario comentario = comentarioRepo.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFound("Comentario", "id", comentarioId));
		
		/*if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}*/
		
		comentarioRepo.delete(comentario);
	}
}
