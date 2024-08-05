package com.c1848tjavaangular.domi.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
@Builder
public class SolicitudesDto implements Serializable {
    private Integer idSolicitud;
    private Integer idServicioProfesion;
    private Integer idUsuario;
    private String mensaje;
    private String estado;
    private String resena;
    private Integer estrellas;
}
