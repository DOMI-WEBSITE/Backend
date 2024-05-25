package com.c1848tjavaangular.domi.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    @Column(nullable = false, unique = true)
    @NotNull
    private String email;
    private Date fecha_nacimiento;
    private String direccion;
    @Column(nullable = false)
    @NotNull
    private Boolean isProfesional;
    
    
}