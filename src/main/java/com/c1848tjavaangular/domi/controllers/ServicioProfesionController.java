package com.c1848tjavaangular.domi.controllers;

import com.c1848tjavaangular.domi.auth.jwt.JwtService;
import com.c1848tjavaangular.domi.dtos.ProfesionalDto;
import com.c1848tjavaangular.domi.dtos.ServicioProfesionDto;
import com.c1848tjavaangular.domi.dtos.ServiciosProfesionalDto;
import com.c1848tjavaangular.domi.services.ServicioProfesionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Permite ver todo los profesionales y sus servicios ofrecidos")
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

    @Operation(summary = "Permite ver el perfil de un profesional y sus servicios ofrecidos")
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

    @Operation(summary = "Permite buscar profesionales por nombre de servicio")
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

    @Operation(summary = "Permite a un profesional publicar sus servicios", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/servicio-profesion")
    public ResponseEntity<ServicioProfesionDto> save(@RequestHeader("Authorization") String token, @RequestBody ServicioProfesionDto servicioProfesionDto){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return new ResponseEntity<>(servicioProfesionService.save(idUsuario, servicioProfesionDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Permite buscar profesionales por nombre servicio y direccion")
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

    @Operation(summary = "Permite buscar profesionales por direccion")
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

    @Operation(summary = "Permite a un profesional eliminar servicios ofrecidos",security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/servicio-profesion/{id}")
    public ResponseEntity<ServicioProfesionDto> delete(@PathVariable Integer id){
        return ResponseEntity.ok(servicioProfesionService.delete(id));
    }
}
