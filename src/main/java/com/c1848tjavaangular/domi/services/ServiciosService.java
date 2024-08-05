package com.c1848tjavaangular.domi.services;

import java.util.List;

import com.c1848tjavaangular.domi.dtos.ServiciosDto;
import com.c1848tjavaangular.domi.models.entities.Servicios;

public interface ServiciosService {
    public List<ServiciosDto> findAll();

    //public Servicios save(ServiciosDto servicios);

    public ServiciosDto findById(Integer id);

    public List<ServiciosDto> getServiciosByNombre(String nombre);
}
