package com.c1848tjavaangular.domi.mappers;

import com.c1848tjavaangular.domi.dtos.ServicioProfesionDto;
import com.c1848tjavaangular.domi.models.entities.ServicioProfesion;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicioProfesionMapper {

    ServicioProfesion toServicioProfesion(ServicioProfesionDto servicioProfesionDto);

    ServicioProfesionDto toServicioProfesionDto(ServicioProfesion servicioProfesion);

    List<ServicioProfesionDto> toServiciosProfesionDtos(List<ServicioProfesion> servicioProfesions);

    void updateServicio(@MappingTarget ServicioProfesion servicioProfesion, ServicioProfesionDto servicioProfesionDto);
}
