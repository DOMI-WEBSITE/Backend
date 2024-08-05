package com.c1848tjavaangular.domi.auth.controller;

import com.c1848tjavaangular.domi.auth.dto.JwtResponseDto;
import com.c1848tjavaangular.domi.auth.dto.LoginDto;
import com.c1848tjavaangular.domi.auth.dto.RegisterDto;
import com.c1848tjavaangular.domi.auth.service.AuthService;
import com.c1848tjavaangular.domi.models.entities.Usuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto loginDto) {

            return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuarios> register(@RequestBody RegisterDto registerDto) {

        return new ResponseEntity<>(authService.registro(registerDto), HttpStatus.CREATED);
    }

}
