package com.c1848tjavaangular.domi.services.impl;

import java.util.List;

import com.c1848tjavaangular.domi.exceptions.AppNotFoundException;
import com.c1848tjavaangular.domi.mappers.ServiciosMapper;
import org.springframework.stereotype.Service;

import com.c1848tjavaangular.domi.dtos.ServiciosDto;
import com.c1848tjavaangular.domi.models.entities.Servicios;
import com.c1848tjavaangular.domi.repository.ServiciosRepository;
import com.c1848tjavaangular.domi.services.ServiciosService;

import jakarta.transaction.Transactional;
@Service
public class ServiciosServiceImpl implements ServiciosService{

    private final ServiciosRepository serviciosRepository;
    private final ServiciosMapper serviciosMapper;

    public ServiciosServiceImpl(ServiciosRepository serviciosRepository, ServiciosMapper serviciosMapper){
        this.serviciosRepository = serviciosRepository;
        this.serviciosMapper = serviciosMapper;
    }

    @Override
    public List<ServiciosDto> findAll() {
        return serviciosMapper.toServiciosDtos(serviciosRepository.findAll());
    }

    @Transactional
    @Override
    public ServiciosDto findById(Integer id) {
        Servicios servicios = serviciosRepository.findById(id)
                .orElseThrow(()-> new AppNotFoundException("Servicio not found!"));
        
        return serviciosMapper.toServiciosDto(servicios);
    }

    @Override
    public List<ServiciosDto> getServiciosByNombre(String nombre) {
        return serviciosMapper.toServiciosDtos(serviciosRepository.findByNombre(nombre));
    }

}
