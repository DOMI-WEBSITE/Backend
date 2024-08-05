package com.c1848tjavaangular.domi.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "recuperar_password")
public class ResetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(name = "fecha_creacion")
    private Date createdDate = new Date();

    @Column(name = "fecha_expiracion")
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuarios user;

    public ResetPassword() {}

    public ResetPassword(String token, Usuarios user) {
        this.token = token;
        this.user = user;
        this.expirationDate = calcularExpiracion();
    }

    public Date calcularExpiracion(){
        long expirationTime = 600000; //10 minutos
        return new Date(createdDate.getTime() + expirationTime);
    }

    public boolean isExpired() {
        return new Date().after(this.expirationDate);
    }
}
