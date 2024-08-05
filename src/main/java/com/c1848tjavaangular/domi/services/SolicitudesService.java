package com.c1848tjavaangular.domi.services;

import java.util.List;

import com.c1848tjavaangular.domi.dtos.SolicitudesDto;
import com.c1848tjavaangular.domi.dtos.SolicitudesProfesionalDto;
import com.c1848tjavaangular.domi.dtos.SolicitudesUsuarioDto;
import com.c1848tjavaangular.domi.models.entities.Solicitudes;

public interface SolicitudesService {
    List<SolicitudesDto> findAll();

    SolicitudesDto save(Integer idUsuario, SolicitudesDto solicitudesDto);

    SolicitudesDto findById(Integer id);

    SolicitudesDto update(Integer idSolicitud, SolicitudesDto solicitudesDto);

    SolicitudesDto findByIdUsuario(Integer idUsuario);

    SolicitudesDto delete(Integer idSolicitud);

    // Todas las solicitudes que ha recibido un usuario profesional
    List<SolicitudesUsuarioDto> findSolicitudesByProfessionalId(Integer idUsuario);

    // Todas las solicitudes que ha recibido un usuario profesional Y las ha terminado
    List<SolicitudesUsuarioDto> findSolicitudesTerminadasByProfessionalId(Integer idUsuario);

    // Todas las solicitudes que ha recibido un usuario profesional y estan sin realizar
    List<SolicitudesUsuarioDto> findSolicitudesPendientesByProfessionalId(Integer idUsuario);

    // Todas las solicitudes que ha hecho un usuario
    List<SolicitudesProfesionalDto> findSolicitudesByUserId(Integer idUsuario);

    // Todas las solicitudes que ha hecho un usuario y se las han terminado
    List<SolicitudesProfesionalDto> findSolicitudesTerminadasByUserId(Integer idUsuario);

    // Todas las solicitudes que ha hecho un usuario y estan sin realizar
    List<SolicitudesProfesionalDto> findSolicitudesPendientesByUserId(Integer idUsuario);
}
