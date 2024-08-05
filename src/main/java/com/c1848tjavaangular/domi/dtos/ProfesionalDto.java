package com.c1848tjavaangular.domi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionalDto {
    private Integer idUsuario;
    private String nombreProfesional;
    private String apellidosProfesional;
    private String direccionProfesional;
    private String fotoPerfil;
    private String fotoPortada;
    private String servicios;
}
