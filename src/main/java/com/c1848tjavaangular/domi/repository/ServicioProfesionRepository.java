package com.c1848tjavaangular.domi.repository;

import com.c1848tjavaangular.domi.dtos.ServiciosProfesionalDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.c1848tjavaangular.domi.models.entities.ServicioProfesion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioProfesionRepository extends JpaRepository<ServicioProfesion, Integer> {

    // Todos los usuarios profesionales con sus servicios
    @Query(value = "SELECT u.idusuario, u.nombre, u.apellidos, u.direccion, u.foto AS fotoPerfil, " +
            "u.portada AS fotoPortada, " +
            "GROUP_CONCAT(s.nombre SEPARATOR ', ') AS servicios " +
            "FROM usuarios u " +
            "LEFT JOIN servicio_profesion sp ON u.idusuario = sp.idusuario " +
            "LEFT JOIN servicios s ON sp.idservicio = s.idservicio " +
            "WHERE u.isProfesional = 1 " +
            "GROUP BY u.idusuario, u.nombre, u.apellidos, u.direccion, u.foto",
            nativeQuery = true)
    List<Object[]> findAllProfesionales();

    // Todos los servicios del profesional por ID
    @Query("SELECT new com.c1848tjavaangular.domi.dtos.ServiciosProfesionalDto(sp.id, u.nombre, u.apellidos, u.sobreMi, u.foto, u.portada, u.telefono, u.email, u.direccion, s.nombre, s.foto, sp.descripcion) \n" +
            "FROM Usuarios u JOIN ServicioProfesion sp ON u.id = sp.usuario.id JOIN Servicios s ON sp.servicio.id = s.id WHERE u.id =:idUsuario")
    List<ServiciosProfesionalDto> findAllServiciosProfesionByIdUsuario(Integer idUsuario);

    // Usuarios profesionales con sus servicios por nombre servicio
    @Query(value = "SELECT u.idusuario, u.nombre, u.apellidos, u.direccion, u.foto AS fotoPerfil, " +
            "u.portada AS fotoPortada, " +
            "GROUP_CONCAT(s.nombre SEPARATOR ', ') AS servicios " +
            "FROM usuarios u " +
            "LEFT JOIN servicio_profesion sp ON u.idusuario = sp.idusuario " +
            "LEFT JOIN servicios s ON sp.idservicio = s.idservicio " +
            "WHERE u.isProfesional = 1 AND s.nombre LIKE %:nombreServicio% " +
            "GROUP BY u.idusuario, u.nombre, u.apellidos, u.direccion, u.foto",
            nativeQuery = true)
    List<Object[]> findProfesionalesByNombreServicio(String nombreServicio);

    // Usuarios profesionales con sus servicios por nombre servicio y direccion
    @Query(value = "SELECT u.idusuario, u.nombre, u.apellidos, u.direccion, u.foto AS fotoPerfil, " +
            "u.portada AS fotoPortada, " +
            "GROUP_CONCAT(s.nombre SEPARATOR ', ') AS servicios " +
            "FROM usuarios u " +
            "LEFT JOIN servicio_profesion sp ON u.idusuario = sp.idusuario " +
            "LEFT JOIN servicios s ON sp.idservicio = s.idservicio " +
            "WHERE u.isProfesional = 1 AND s.nombre LIKE %:nombreServicio% AND u.direccion LIKE %:direccionUsuario% " +
            "GROUP BY u.idusuario, u.nombre, u.apellidos, u.direccion, u.foto",
            nativeQuery = true)
    List<Object[]> findProfesionalByNombreServicioAndDireccion(String nombreServicio, String direccionUsuario);

    // Usuarios profesionales con sus servicios por direccion
    @Query(value = "SELECT u.idusuario, u.nombre, u.apellidos, u.direccion, u.foto AS fotoPerfil, " +
            "u.portada AS fotoPortada, " +
            "GROUP_CONCAT(s.nombre SEPARATOR ', ') AS servicios " +
            "FROM usuarios u " +
            "LEFT JOIN servicio_profesion sp ON u.idusuario = sp.idusuario " +
            "LEFT JOIN servicios s ON sp.idservicio = s.idservicio " +
            "WHERE u.isProfesional = 1 AND u.direccion LIKE %:direccionUsuario% " +
            "GROUP BY u.idusuario, u.nombre, u.apellidos, u.direccion, u.foto",
            nativeQuery = true)
    List<Object[]> findProfesionalByDireccion(String direccionUsuario);

    // Usuario profesional con sus servicio por idServicio
    @Query("SELECT new com.c1848tjavaangular.domi.dtos.ServiciosProfesionalDto(sp.id, u.nombre, u.apellidos, u.sobreMi, u.foto, u.portada, u.telefono, u.email, u.direccion, s.nombre, s.foto, sp.descripcion) \n" +
            "FROM Usuarios u \n" +
            "JOIN ServicioProfesion sp ON u.id = sp.usuario.id \n" +
            "JOIN Servicios s ON sp.servicio.id = s.id WHERE sp.id =:idServicioP")
    ServiciosProfesionalDto findServicioProfesionalById(Integer idServicioP);

    // Obtener el email del usuario profesional por el idservicio_profesion
    @Query(value = "SELECT  us.email\n" +
            "FROM servicio_profesion sp \n" +
            "JOIN usuarios us ON sp.idusuario = us.idusuario\n" +
            "WHERE sp.idservicio_profesion =:idServicioP", nativeQuery = true)
    String emailByIdServicioProfesional(Integer idServicioP);

    // Obtener el username del usuario profesional por el idservicio_profesion
    @Query(value = "SELECT  us.username\n" +
            "FROM servicio_profesion sp \n" +
            "JOIN usuarios us ON sp.idusuario = us.idusuario\n" +
            "WHERE sp.idservicio_profesion =:idServicioP", nativeQuery = true)
    String usernameByIdServicioProfesional(Integer idServicioP);

}
