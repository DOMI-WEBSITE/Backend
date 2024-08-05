package com.c1848tjavaangular.domi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudesProfesionalDto {
    private Integer idSolicitud;
    private String profesional;
    private String telefonoProfesional;
    private String direccionProfesional;
    private String nombreServicio;
    private String mensaje;
    private String estado;
    private Integer estrellas;
}
