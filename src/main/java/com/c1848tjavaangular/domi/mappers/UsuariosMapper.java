package com.c1848tjavaangular.domi.mappers;

import com.c1848tjavaangular.domi.dtos.UsuariosDto;
import com.c1848tjavaangular.domi.models.entities.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    Usuarios toUsuarios(UsuariosDto usuariosDto);

    UsuariosDto toUsuariosDto(Usuarios usuarios);

    @Mapping(target = "password", ignore = true)
    void updateUsuario(@MappingTarget Usuarios usuarios, UsuariosDto usuariosDto);

}
