package com.c1848tjavaangular.domi.auth.service;

import com.c1848tjavaangular.domi.auth.dto.JwtResponseDto;
import com.c1848tjavaangular.domi.auth.dto.LoginDto;
import com.c1848tjavaangular.domi.auth.dto.RegisterDto;
import com.c1848tjavaangular.domi.auth.jwt.JwtService;
import com.c1848tjavaangular.domi.exceptions.AppConflictException;
import com.c1848tjavaangular.domi.exceptions.AppNotFoundException;
import com.c1848tjavaangular.domi.exceptions.JwtAuthenticationException;
import com.c1848tjavaangular.domi.models.entities.Usuarios;
import com.c1848tjavaangular.domi.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImple implements AuthService {
    @Autowired
    private final UsuariosRepository usuariosRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImple(UsuariosRepository usuariosRepository, AuthenticationManager authenticationManager,
                            JwtService jwtService, PasswordEncoder passwordEncoder){
        this.usuariosRepository = usuariosRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuarios registro(RegisterDto registerDto) {

        Boolean existsUsername = usuariosRepository.existsByUsername(registerDto.getUsername());
        Boolean existsEmail = usuariosRepository.existsByEmail(registerDto.getEmail());

        if (existsUsername){
            throw new AppConflictException("Existe username!");
        } else if (existsEmail) {
            throw new AppConflictException("Existe email!");
        }else {
            Usuarios user = new Usuarios();
            user.setNombre(registerDto.getNombre());
            user.setApellidos(registerDto.getApellidos());
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setEmail(registerDto.getEmail());
            user.setTelefono(registerDto.getTelefono());
            user.setDireccion(registerDto.getDireccion());
            user.setIsProfesional(registerDto.getIsProfesional());

            return usuariosRepository.save(user);
        }
    }

    @Override
    public JwtResponseDto login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
            Usuarios user =  usuariosRepository.findByEmail(loginDto.getEmail())
                            .orElseThrow(()-> new AppNotFoundException("User not found"));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtService.getToken(user);
            return new JwtResponseDto(token);
        } catch (AuthenticationException e) {
            throw new JwtAuthenticationException("Credenciales inválidas");
        }
    }

}
