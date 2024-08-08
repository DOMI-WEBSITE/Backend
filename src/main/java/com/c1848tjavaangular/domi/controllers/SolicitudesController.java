package com.c1848tjavaangular.domi.controllers;

import com.c1848tjavaangular.domi.auth.jwt.JwtService;
import com.c1848tjavaangular.domi.dtos.SolicitudesDto;
import com.c1848tjavaangular.domi.dtos.SolicitudesProfesionalDto;
import com.c1848tjavaangular.domi.dtos.SolicitudesUsuarioDto;
import com.c1848tjavaangular.domi.services.ServicioProfesionService;
import com.c1848tjavaangular.domi.services.SolicitudesService;
import com.c1848tjavaangular.domi.services.UsuariosService;
import com.c1848tjavaangular.domi.services.impl.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SolicitudesController {

    private final SolicitudesService solicitudesService;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final ServicioProfesionService servicioProfesionService;
    private final UsuariosService usuariosService;

    public SolicitudesController(SolicitudesService solicitudesService, JwtService jwtService, EmailService emailService, ServicioProfesionService servicioProfesionService, UsuariosService usuariosService){
        this.solicitudesService = solicitudesService;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.servicioProfesionService = servicioProfesionService;
        this.usuariosService = usuariosService;
    }

    @Operation(summary = "Permite ver las solicitudes", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/solicitud")
    public ResponseEntity<List<SolicitudesDto>> get(){
        return ResponseEntity.ok( solicitudesService.findAll());
    }

    @Operation(summary = "Permite a un usuario enviar solicitud al profesional", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/solicitud")
    public ResponseEntity<SolicitudesDto> save(@RequestHeader("Authorization") String token,@RequestBody SolicitudesDto solicitudesDto){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        // Obtener el email del usuario profesional
        String emailUsuario = servicioProfesionService.getEmailUsuario(solicitudesDto.getIdServicioProfesion());
        // Obtener el username del usuario profesional
        String username = servicioProfesionService.getUsername(solicitudesDto.getIdServicioProfesion());
        // Obtener el telefono del usuario que solicita el servicio
        String telefono = usuariosService.getTelefono(idUsuario);
        // para que le llegue un correo al usuario profesional cuando se solicite su servicio
        String url = "http://localhost:8095/api/solicitud"; // url de solicitudes del usuario
        String urlWhatsapp = "https://wa.me/"+telefono; // URL para establecer una comunicacion entre el usuario profesional y usuario solicitante
        emailService.sendSimpleMessage(emailUsuario, "Solicitudes pendientes", "Hola "+username+" \nEstan solicitando tus servicios, click en el link para iniciar una comunicación con el solicitante: " + urlWhatsapp);

        return new ResponseEntity<>(solicitudesService.save(idUsuario,solicitudesDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Permite a un usuario actualizar la solicitud", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/solicitud/{idSolicitud}")
    public ResponseEntity<SolicitudesDto> update(@PathVariable Integer idSolicitud,@RequestBody SolicitudesDto solicitudesDto){
        //Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return new ResponseEntity<>(solicitudesService.update(idSolicitud,solicitudesDto), HttpStatus.OK);
    }

    @Operation(summary = "Permite a un usuario eliminar solicitud", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/solicitud/{id}")
    public ResponseEntity<SolicitudesDto> delete(@PathVariable Integer id){
        return ResponseEntity.ok(solicitudesService.delete(id));
    }

    @Operation(summary = "Permite ver las solicitudes que ha recibido un profesional", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/solicitud/profesional")
    public ResponseEntity<List<SolicitudesUsuarioDto>> getSolicitudesProfesional(@RequestHeader("Authorization") String token){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return ResponseEntity.ok(solicitudesService.findSolicitudesByProfessionalId(idUsuario));
    }

    @Operation(summary = "Permite ver las solicitudes que ha recibido el profesional y las ha terminado", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/solicitud/profesional/terminada")
    public ResponseEntity<List<SolicitudesUsuarioDto>> getSolicitudesTerminadasProfesional(@RequestHeader("Authorization") String token){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return ResponseEntity.ok(solicitudesService.findSolicitudesTerminadasByProfessionalId(idUsuario));
    }

    @Operation(summary = "Permite ver las solicitudes que ha recibido el profesional y estan sin realizar", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/solicitud/profesional/pendiente")
    public ResponseEntity<List<SolicitudesUsuarioDto>> getSolicitudesPendientesPorfesional(@RequestHeader("Authorization") String token){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return ResponseEntity.ok(solicitudesService.findSolicitudesPendientesByProfessionalId(idUsuario));
    }

    @Operation(summary = "Permite ver las solicitudes que ha realizado un usuario", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/solicitud/usuario")
    public ResponseEntity<List<SolicitudesProfesionalDto>> getSolicitudesUsuario(@RequestHeader("Authorization") String token){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return ResponseEntity.ok(solicitudesService.findSolicitudesByUserId(idUsuario));
    }

    @Operation(summary = "Permite ver las solicitudes que ha realizado un usuario y estan terminadas", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/solicitud/usuario/terminada")
    public ResponseEntity<List<SolicitudesProfesionalDto>> getSolicitudesTerminadasUsuario(@RequestHeader("Authorization") String token){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return ResponseEntity.ok(solicitudesService.findSolicitudesTerminadasByUserId(idUsuario));
    }

    @Operation(summary = "Permite ver las solicitudes que ha realizado un usuario y estan sin terminar", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/solicitud/usuario/pendiente")
    public ResponseEntity<List<SolicitudesProfesionalDto>> getSolicitudesPendientesUsuario(@RequestHeader("Authorization") String token){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return ResponseEntity.ok(solicitudesService.findSolicitudesPendientesByUserId(idUsuario));
    }

}
