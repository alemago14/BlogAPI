package com.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entidades.Comentario;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, Long>{

	public List<Comentario> findByPublicacionId(Long publicacionId);
}
