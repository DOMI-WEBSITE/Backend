package com.c1848tjavaangular.domi.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    @NotNull
    private String nombre;
    @NotNull
    private String apellidos;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String telefono;
    @NotNull
    private String direccion;
    @NotNull
    private Boolean isProfesional;

}
