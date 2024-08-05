package com.c1848tjavaangular.domi.models.entities;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "servicio_profesion")
@Entity

public class ServicioProfesion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservicio_profesion")
    private Integer idServicioProfesion;
    @Column(name = "idservicio")
    private Integer idServicio;
    @Column(name = "idusuario")
    private Integer idUsuario;
    private String descripcion;

    @ManyToOne
     @JoinColumn(name = "idservicio", insertable=false, updatable=false)
     private Servicios servicio;
    @ManyToOne
     @JoinColumn(name = "idusuario", insertable=false, updatable=false)
     private Usuarios usuario;

}
