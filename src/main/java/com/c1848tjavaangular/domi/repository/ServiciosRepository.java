package com.c1848tjavaangular.domi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.c1848tjavaangular.domi.models.entities.Servicios;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Integer> {

    List<Servicios> findByNombre(String nombre);
    
}
