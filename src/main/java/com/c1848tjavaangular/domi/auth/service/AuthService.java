package com.c1848tjavaangular.domi.auth.service;


import com.c1848tjavaangular.domi.auth.dto.JwtResponseDto;
import com.c1848tjavaangular.domi.auth.dto.LoginDto;
import com.c1848tjavaangular.domi.auth.dto.RegisterDto;
import com.c1848tjavaangular.domi.models.entities.Usuarios;

public interface AuthService {

    public Usuarios registro(RegisterDto registerDto);

    public JwtResponseDto login(LoginDto loginDto);
}
