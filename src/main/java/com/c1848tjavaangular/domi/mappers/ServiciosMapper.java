package com.c1848tjavaangular.domi.mappers;

import com.c1848tjavaangular.domi.dtos.ServiciosDto;
import com.c1848tjavaangular.domi.models.entities.Servicios;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiciosMapper {

    ServiciosDto toServiciosDto(Servicios servicios);

    List<ServiciosDto> toServiciosDtos(List<Servicios> servicios);
}
