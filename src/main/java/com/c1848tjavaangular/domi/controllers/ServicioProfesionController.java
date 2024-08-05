package com.c1848tjavaangular.domi.controllers;

import com.c1848tjavaangular.domi.auth.jwt.JwtService;
import com.c1848tjavaangular.domi.dtos.ProfesionalDto;
import com.c1848tjavaangular.domi.dtos.ServicioProfesionDto;
import com.c1848tjavaangular.domi.dtos.ServiciosProfesionalDto;
import com.c1848tjavaangular.domi.services.ServicioProfesionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServicioProfesionController {

    private final ServicioProfesionService servicioProfesionService;
    private final JwtService jwtService;

    @Value("${baseUrl}")
    String baseUrl; //url de las imagenes

    public ServicioProfesionController(ServicioProfesionService servicioProfesionService, JwtService jwtService){
        this.servicioProfesionService = servicioProfesionService;
        this.jwtService = jwtService;
    }

    @GetMapping("/servicio-profesion")
    public ResponseEntity<?> getProfesionales(){
        List<ProfesionalDto> profesionales = servicioProfesionService.getProfesionales();
        for (ProfesionalDto profesional : profesionales) {
            String fotoPerfilUrl = baseUrl + "fotos/" + profesional.getFotoPerfil();
            String fotoPortadaUrl = baseUrl + "portada/" + profesional.getFotoPortada();
            profesional.setFotoPerfil(fotoPerfilUrl);
            profesional.setFotoPortada(fotoPortadaUrl);
        }
        return ResponseEntity.ok(profesionales);
    }

    @GetMapping("/servicio-profesion/{idUsuario}")
    public ResponseEntity<List<ServiciosProfesionalDto>> getServicioProfesionalByIdUsuario(@PathVariable Integer idUsuario){
        List<ServiciosProfesionalDto> serviciosProfesional  = servicioProfesionService.getServiciosProfesionByIdUsuario(idUsuario);
        for (ServiciosProfesionalDto servicios : serviciosProfesional) {
            String fotoPerfilUrl = baseUrl + "fotos/" + servicios.getFoto();
            String fotoPortadaUrl = baseUrl + "portada/" + servicios.getPortada();
            String fotoServicioUrl = baseUrl + "servicios/" + servicios.getFotoServicio();
            servicios.setFoto(fotoPerfilUrl);
            servicios.setPortada(fotoPortadaUrl);
            servicios.setFotoServicio(fotoServicioUrl);
        }
        return ResponseEntity.ok(serviciosProfesional);
    }
    
    @GetMapping("/servicio-profesion/nombre")
    public ResponseEntity<?> getProfesionalesByNombreServicio(@RequestParam String nombreServicio){
        List<ProfesionalDto> profesionales = servicioProfesionService.getProfesionalesByNombreServicio(nombreServicio);
        for (ProfesionalDto profesional : profesionales) {
            String fotoPerfilUrl = baseUrl + "fotos/" + profesional.getFotoPerfil();
            String fotoPortadaUrl = baseUrl + "portada/" + profesional.getFotoPortada();
            profesional.setFotoPerfil(fotoPerfilUrl);
            profesional.setFotoPortada(fotoPortadaUrl);
        }
        return ResponseEntity.ok(profesionales);
    }

    @PostMapping("/servicio-profesion")
    public ResponseEntity<ServicioProfesionDto> save(@RequestHeader("token") String token, @RequestBody ServicioProfesionDto servicioProfesionDto){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return new ResponseEntity<>(servicioProfesionService.save(idUsuario, servicioProfesionDto), HttpStatus.CREATED);
    }

    @GetMapping("/servicio-profesion/nombre-direccion")
    public ResponseEntity<?> getProfesionalByNombreServicioAndDireccion(@RequestParam String nombreServicio, @RequestParam String direccion){
        List<ProfesionalDto> profesionales = servicioProfesionService.getProfesionalByNombreServicioAndDireccion(nombreServicio, direccion);
        for (ProfesionalDto profesional : profesionales) {
            String fotoPerfilUrl = baseUrl + "fotos/" + profesional.getFotoPerfil();
            String fotoPortadaUrl = baseUrl + "portada/" + profesional.getFotoPortada();
            profesional.setFotoPerfil(fotoPerfilUrl);
            profesional.setFotoPortada(fotoPortadaUrl);
        }
        return ResponseEntity.ok(profesionales);
    }

    @GetMapping("/servicio-profesion/direccion")
    public ResponseEntity<?> getProfesionalByDireccion(@RequestParam String direccion){
        List<ProfesionalDto> profesionales = servicioProfesionService.getProfesionalByDireccion(direccion);
        for (ProfesionalDto profesional : profesionales) {
            String fotoPerfilUrl = baseUrl + "fotos/" + profesional.getFotoPerfil();
            String fotoPortadaUrl = baseUrl + "portada/" + profesional.getFotoPortada();
            profesional.setFotoPerfil(fotoPerfilUrl);
            profesional.setFotoPortada(fotoPortadaUrl);
        }
        return ResponseEntity.ok(profesionales);

    }

    @DeleteMapping("/servicio-profesion/{id}")
    public ResponseEntity<ServicioProfesionDto> delete(@PathVariable Integer id){
        return ResponseEntity.ok(servicioProfesionService.delete(id));
    }
}
