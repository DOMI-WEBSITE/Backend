package com.c1848tjavaangular.domi.controllers;

import com.c1848tjavaangular.domi.auth.jwt.JwtService;
import com.c1848tjavaangular.domi.dtos.PasswordDto;
import com.c1848tjavaangular.domi.dtos.UsuariosDto;
import com.c1848tjavaangular.domi.services.UsuariosService;
import com.c1848tjavaangular.domi.services.impl.UploadFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UsuariosController {

    private final UsuariosService usuariosService;
    private final JwtService jwtService;
    private final UploadFileService uploadFileService;

    @Value("${baseUrl}")
    String baseUrl; // url de la ruta de las imagenes

    public UsuariosController(UsuariosService usuariosService, JwtService jwtService, UploadFileService uploadFileService){
        this.usuariosService = usuariosService;
        this.jwtService = jwtService;
        this.uploadFileService = uploadFileService;
    }
    @Operation(summary = "Permite actualizar perfil del usuario", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping(value = "/usuario",consumes = "multipart/form-data")
    public ResponseEntity<?> updatePerfil(@RequestPart("foto") MultipartFile foto, @RequestPart("portada") MultipartFile portada,@RequestHeader("Authorization") String token, @RequestPart UsuariosDto usuariosDto) throws IOException {
        String nombreFoto = uploadFileService.saveFoto(foto);
        String nombrePortada = uploadFileService.savePortada(portada);
        usuariosDto.setFoto(nombreFoto);
        usuariosDto.setPortada(nombrePortada);
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        return ResponseEntity.ok().body(usuariosService.updatePerfil(idUsuario, usuariosDto));
    }

    @Operation(summary = "Permite actualizar password", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/usuario/password")
    public ResponseEntity<?> updatePassword(@RequestHeader("Authorization") String token, @RequestBody PasswordDto passworDto) {
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        usuariosService.updatePassword(idUsuario, passworDto);
        return ResponseEntity.ok().body("Contraseña actualizada!");
    }

    @Operation(summary = "Permite obtener perfil del usuario", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/usuario/perfil")
    public ResponseEntity<?> getUsuario(@RequestHeader("Authorization") String token){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        UsuariosDto usuariosDto = usuariosService.findById(idUsuario);

        usuariosDto.setFoto(baseUrl + "fotos/" + usuariosDto.getFoto());
        usuariosDto.setPortada(baseUrl + "portada/" + usuariosDto.getPortada());
        return ResponseEntity.ok(usuariosDto);

    }
}
