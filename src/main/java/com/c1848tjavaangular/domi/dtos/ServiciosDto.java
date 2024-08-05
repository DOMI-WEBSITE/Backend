package com.c1848tjavaangular.domi.dtos;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
@Builder
public class ServiciosDto implements Serializable {
    private Integer idServicio;
    private String nombre;
    private String foto;
}
