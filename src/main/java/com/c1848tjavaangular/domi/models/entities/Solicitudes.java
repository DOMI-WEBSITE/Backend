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
@NoArgsConstructor
@Builder
@Table(name="solicitudes")
@Entity
public class Solicitudes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idsolicitud")
    private Integer idSolicitud;
    @Column(name = "idservicio_profesion")
    private Integer idServicioProfesion;
    @Column(name = "idusuario")
    private Integer idUsuario;
    private String mensaje;
    private String estado;
    private String resena;
    private Integer estrellas;

    @ManyToOne 
     @JoinColumn(name="idservicio_profesion", insertable=false, updatable=false)
     private ServicioProfesion servicioProfesion;
    @ManyToOne
     @JoinColumn(name="idusuario", insertable=false, updatable=false)
     private Usuarios usuario;

}
