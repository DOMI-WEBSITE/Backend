package com.c1848tjavaangular.domi.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuarios")
@Entity
public class Usuarios implements Serializable {
    @Id
    @Column(name="idusuario", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarios;
    @Column(nullable = false)
    @NotNull
    private String nombre;
    @Column(nullable = false)
    @NotNull
    private String apellidos;
    @Column(nullable = false, unique = true)
    @NotNull
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @NotNull
    private String telefono;
    private String foto;
    private String portada;
    @Column(name = "sobre_mi")
    private String sobreMi;
    @Column(nullable = false, unique = true)
    @NotNull
    @Email
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_nacimiento;
    @NotNull
    private String direccion;
    @NotNull
    @Column(name = "isprofesional",nullable = false)
    private Boolean isProfesional;
    
    
}
