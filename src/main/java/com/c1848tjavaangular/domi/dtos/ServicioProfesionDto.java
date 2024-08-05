package com.c1848tjavaangular.domi.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
@Builder
public class ServicioProfesionDto implements Serializable{
    private Integer idServicioProfesion;
    private Integer idUsuario;
    private Integer idServicio;
    private String descripcion;
}
