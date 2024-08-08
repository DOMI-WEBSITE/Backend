package com.c1848tjavaangular.domi.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Permite a un profesional ver los servicios que puede brindar u ofrecer")
    @GetMapping("/servicios")
    public ResponseEntity<List<ServiciosDto>> get() {
        return ResponseEntity.ok(serviciosService.findAll());

    }

    @Operation(summary = "Permite a un profesional buscar servicios por nombre para brindar u ofrecer")
    @GetMapping("/servicios/nombre")
    public ResponseEntity<List<ServiciosDto>> getServiciosByNombre(@RequestParam String nombre){
        return ResponseEntity.ok(serviciosService.getServiciosByNombre(nombre));
    }
}
