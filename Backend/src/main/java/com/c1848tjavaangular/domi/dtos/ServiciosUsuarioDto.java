package com.c1848tjavaangular.domi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiciosUsuarioDto {
    private Integer idServicioProfesion;
    private String nombreUsuario;
    private String apellidosUsuario;
    private String sobreMi;
    private String foto;
    private String telefonoUsuario;
    private String emailUsuario;
    private String direccionUsuario;
    private String nombreServicio;
}
