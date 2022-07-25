package com.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entidades.Publicacion;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long>{

}
