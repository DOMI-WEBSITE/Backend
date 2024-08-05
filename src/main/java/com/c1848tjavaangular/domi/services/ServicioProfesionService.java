package com.c1848tjavaangular.domi.services;

import java.util.List;

import com.c1848tjavaangular.domi.dtos.ProfesionalDto;
import com.c1848tjavaangular.domi.dtos.ServicioProfesionDto;
import com.c1848tjavaangular.domi.dtos.ServiciosProfesionalDto;


public interface ServicioProfesionService {
    // Todos los usuarios profesionales con sus servicios
    List<ProfesionalDto> getProfesionales();

    List<ServiciosProfesionalDto> getServiciosProfesionByIdUsuario(Integer idUsuario);

    ServicioProfesionDto save(Integer idUsuario,ServicioProfesionDto servicioProfesionDto);

    ServicioProfesionDto findById(Integer id);

    // Usuarios profesionales con sus servicios por nombre servicio
    List<ProfesionalDto> getProfesionalesByNombreServicio(String nombre);

    // Usuarios profesionales con sus servicios por nombre servicio y direccion
    List<ProfesionalDto> getProfesionalByNombreServicioAndDireccion(String nombreServicio, String direccionProfesional);

    // Usuarios profesionales con sus servicios por direccion
    List<ProfesionalDto> getProfesionalByDireccion(String direccionProfesional);

    ServiciosProfesionalDto getServicioProfesionalById(Integer id);

    ServicioProfesionDto delete(Integer id);

    String getEmailUsuario(Integer idServicioP);

    String getUsername(Integer idServicioP);
}
