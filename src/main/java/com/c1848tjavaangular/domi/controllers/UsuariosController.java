package com.c1848tjavaangular.domi.controllers;

import com.c1848tjavaangular.domi.auth.jwt.JwtService;
import com.c1848tjavaangular.domi.dtos.PasswordDto;
import com.c1848tjavaangular.domi.dtos.UsuariosDto;
import com.c1848tjavaangular.domi.services.UsuariosService;
import com.c1848tjavaangular.domi.services.impl.UploadFileService;
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
    @PutMapping(value = "/usuario/{idUsuario}",consumes = "multipart/form-data")
    public ResponseEntity<?> updatePerfil(@RequestPart("foto") MultipartFile foto, @RequestPart("portada") MultipartFile portada,@PathVariable Integer idUsuario, @RequestPart UsuariosDto usuariosDto) throws IOException {
        String nombreFoto = uploadFileService.saveFoto(foto);
        String nombrePortada = uploadFileService.savePortada(portada);
        usuariosDto.setFoto(nombreFoto);
        usuariosDto.setPortada(nombrePortada);
        usuariosService.updatePerfil(idUsuario, usuariosDto);
        return ResponseEntity.ok().body("Perfil actualizado!");
    }

    @PutMapping("/usuario/password")
    public ResponseEntity<?> updatePassword(@RequestHeader("token") String token, @RequestBody PasswordDto passworDto) {
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        usuariosService.updatePassword(idUsuario, passworDto);
        return ResponseEntity.ok().body("Contraseña actualizada! ");
    }

    @GetMapping("/usuario/perfil")
    public ResponseEntity<?> getUsuario(@RequestHeader("token") String token){
        Integer idUsuario = jwtService.getIdUsuarioFromToken(token);
        UsuariosDto usuariosDto = usuariosService.findById(idUsuario);

        usuariosDto.setFoto(baseUrl + "fotos/" + usuariosDto.getFoto());
        usuariosDto.setPortada(baseUrl + "portada/" + usuariosDto.getPortada());
        return ResponseEntity.ok(usuariosDto);

    }
}
