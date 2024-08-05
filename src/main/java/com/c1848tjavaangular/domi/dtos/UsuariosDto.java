package com.c1848tjavaangular.domi.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosDto {
    private Integer idUsuarios;
    private String nombre;
    private String apellidos;
    private String username;
    private String email;
    private String telefono;
    private String foto;
    private String portada;
    private String sobreMi;
    private LocalDate fecha_nacimiento;
    private String direccion;
    private Boolean isProfesional;

}
