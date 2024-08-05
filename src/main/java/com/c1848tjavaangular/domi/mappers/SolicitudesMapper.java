package com.c1848tjavaangular.domi.mappers;

import com.c1848tjavaangular.domi.dtos.SolicitudesDto;
import com.c1848tjavaangular.domi.models.entities.Solicitudes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SolicitudesMapper {


    Solicitudes toSolicitudes(SolicitudesDto solicitudesDto);


    SolicitudesDto toSolicitudesDto(Solicitudes solicitudes);

    List<SolicitudesDto> toSolicitudesDtos(List<Solicitudes> solicitudes);

    void updateSolicitudes(@MappingTarget Solicitudes solicitudes, SolicitudesDto solicitudesDto);
}
