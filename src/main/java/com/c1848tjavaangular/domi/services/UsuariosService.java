package com.c1848tjavaangular.domi.services;

import com.c1848tjavaangular.domi.dtos.PasswordDto;
import com.c1848tjavaangular.domi.dtos.UsuariosDto;
import com.c1848tjavaangular.domi.models.entities.Usuarios;

public interface UsuariosService {

    public UsuariosDto updatePerfil(Integer idUsuario, UsuariosDto usuariosDto);

    public Usuarios updatePassword(Integer idUsuario, PasswordDto passworDto);

    public UsuariosDto findById(Integer idUsuario);

    public String getTelefono(Integer idUsuario);

}
