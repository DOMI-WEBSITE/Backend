package com.c1848tjavaangular.domi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.c1848tjavaangular.domi.models.entities.Usuarios;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {

    Optional<Usuarios> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT u.telefono FROM Usuarios u WHERE u.idUsuarios = :idUsuario")
    String findTelefonoByIdUsuarios(Integer idUsuario);
    
}
