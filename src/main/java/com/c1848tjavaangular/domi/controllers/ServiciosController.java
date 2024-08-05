package com.c1848tjavaangular.domi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.c1848tjavaangular.domi.auth.jwt.JwtService;
import com.c1848tjavaangular.domi.dtos.ServiciosDto;

import com.c1848tjavaangular.domi.services.ServiciosService;

@RestController
@RequestMapping("/api")
public class ServiciosController {
    private final ServiciosService serviciosService;
    private final JwtService jwtService;

    public ServiciosController(ServiciosService serviciosService, JwtService jwtService){
        this.jwtService= jwtService;
        this.serviciosService= serviciosService;
    }

    @GetMapping("/servicios")
    public ResponseEntity<List<ServiciosDto>> get() {
        return ResponseEntity.ok(serviciosService.findAll());

    }

    @GetMapping("/servicios/nombre")
    public ResponseEntity<List<ServiciosDto>> getServiciosByNombre(@RequestParam String nombre){
        return ResponseEntity.ok(serviciosService.getServiciosByNombre(nombre));
    }
}
