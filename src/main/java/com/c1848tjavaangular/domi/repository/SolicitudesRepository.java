package com.c1848tjavaangular.domi.repository;

import com.c1848tjavaangular.domi.dtos.SolicitudesProfesionalDto;
import com.c1848tjavaangular.domi.dtos.SolicitudesUsuarioDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.c1848tjavaangular.domi.models.entities.Solicitudes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudesRepository extends JpaRepository<Solicitudes, Integer> {
   // Todas las solicitudes que ha recibido un usuario profesional
    @Query("SELECT new com.c1848tjavaangular.domi.dtos.SolicitudesUsuarioDto(so.id, CONCAT(u.nombre, ' ', u.apellidos), u.telefono, u.direccion, s.nombre, so.mensaje, so.estado, so.estrellas) " +
            "FROM Solicitudes so " +
            "JOIN so.usuario u " +
            "JOIN so.servicioProfesion sp " +
            "JOIN sp.servicio s " +
            "WHERE sp.usuario.id = :idUsuario")
    List<SolicitudesUsuarioDto> findSolicitudesByProfessionalId(Integer idUsuario);

    // Todas las solicitudes que ha recibido un usuario profesional Y las ha terminado
    @Query("SELECT new com.c1848tjavaangular.domi.dtos.SolicitudesUsuarioDto(so.id, CONCAT(u.nombre, ' ', u.apellidos), u.telefono, u.direccion, s.nombre, so.mensaje, so.estado, so.estrellas) " +
            "FROM Solicitudes so " +
            "JOIN so.usuario u " +
            "JOIN so.servicioProfesion sp " +
            "JOIN sp.servicio s " +
            "WHERE so.estado = 'Terminada' AND sp.usuario.id = :idUsuario")
    List<SolicitudesUsuarioDto> findSolicitudesTerminadasByProfessionalId(Integer idUsuario);

    // Todas las solicitudes que ha recibido un usuario profesional y estan sin realizar
    @Query("SELECT new com.c1848tjavaangular.domi.dtos.SolicitudesUsuarioDto(so.id, CONCAT(u.nombre, ' ', u.apellidos), u.telefono, u.direccion, s.nombre, so.mensaje, so.estado, so.estrellas) " +
            "FROM Solicitudes so " +
            "JOIN so.usuario u " +
            "JOIN so.servicioProfesion sp " +
            "JOIN sp.servicio s " +
            "WHERE so.estado = 'Pendiente' AND sp.usuario.id = :idUsuario")
    List<SolicitudesUsuarioDto> findSolicitudesPendientesByProfessionalId(Integer idUsuario);

    // Todas las solicitudes que ha hecho un usuario
    @Query("SELECT new com.c1848tjavaangular.domi.dtos.SolicitudesProfesionalDto(so.id, CONCAT(us.nombre, ' ', us.apellidos), us.telefono, us.direccion, s.nombre, so.mensaje, so.estado, so.estrellas) " +
            "FROM Solicitudes so " +
            "JOIN so.usuario u " +
            "JOIN so.servicioProfesion sp " +
            "JOIN sp.usuario us " +
            "JOIN sp.servicio s " +
            "WHERE u.id = :idUsuario")
    List<SolicitudesProfesionalDto> findSolicitudesByUserId(Integer idUsuario);

    // Todas las solicitudes que ha hecho un usuario y se las han terminado
    @Query("SELECT new com.c1848tjavaangular.domi.dtos.SolicitudesProfesionalDto(so.id, CONCAT(us.nombre, ' ', us.apellidos), us.telefono, us.direccion, s.nombre, so.mensaje, so.estado, so.estrellas) " +
            "FROM Solicitudes so " +
            "JOIN so.usuario u " +
            "JOIN so.servicioProfesion sp " +
            "JOIN sp.usuario us " +
            "JOIN sp.servicio s " +
            "WHERE so.estado = 'Terminada' AND u.id = :idUsuario")
    List<SolicitudesProfesionalDto> findSolicitudesTerminadasByUserId(Integer idUsuario);

    // Todas las solicitudes que ha hecho un usuario y estan sin realizar
    @Query("SELECT new com.c1848tjavaangular.domi.dtos.SolicitudesProfesionalDto(so.id, CONCAT(us.nombre, ' ', us.apellidos), us.telefono, us.direccion, s.nombre, so.mensaje, so.estado, so.estrellas) " +
            "FROM Solicitudes so " +
            "JOIN so.usuario u " +
            "JOIN so.servicioProfesion sp " +
            "JOIN sp.usuario us " +
            "JOIN sp.servicio s " +
            "WHERE so.estado = 'Pendiente' AND u.id = :idUsuario")
    List<SolicitudesProfesionalDto> findSolicitudesPendientesByUserId(Integer idUsuario);
    
}
