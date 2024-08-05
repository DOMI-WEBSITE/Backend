package com.c1848tjavaangular.domi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudesUsuarioDto {
    private Integer idSolicitud;
    private String solicitante;
    private String telefonoUsuario;
    private String direccionUsuario;
    private String nombreServicio;
    private String mensaje;
    private String estado;
    private Integer estrellas;
}
