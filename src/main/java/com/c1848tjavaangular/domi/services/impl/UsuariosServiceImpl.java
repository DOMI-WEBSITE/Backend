package com.c1848tjavaangular.domi.services.impl;

import com.c1848tjavaangular.domi.auth.service.UserDetailsServiceImpl;
import com.c1848tjavaangular.domi.dtos.PasswordDto;
import com.c1848tjavaangular.domi.dtos.UsuariosDto;
import com.c1848tjavaangular.domi.exceptions.AppNotFoundException;
import com.c1848tjavaangular.domi.mappers.UsuariosMapper;
import com.c1848tjavaangular.domi.models.entities.Usuarios;
import com.c1848tjavaangular.domi.repository.UsuariosRepository;
import com.c1848tjavaangular.domi.services.UsuariosService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private UsuariosMapper usuariosMapper;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository, UsuariosMapper usuariosMapper, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService){
        this.usuariosRepository = usuariosRepository;
        this.usuariosMapper = usuariosMapper;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    //@Transactional(readOnly = true)
    public UsuariosDto updatePerfil(Integer idUsuario, UsuariosDto usuariosDto) {
        Usuarios usuarios = usuariosRepository.findById(idUsuario)
                .orElseThrow(()-> new AppNotFoundException("Usuario not found"));

        usuariosMapper.updateUsuario(usuarios,usuariosDto);
        return usuariosMapper.toUsuariosDto(usuariosRepository.save(usuarios));

       /* usuarios.setIdUsuarios(usuariosDto.getIdUsuarios());
        usuarios.setNombre(usuariosDto.getNombre());
        usuarios.setApellidos(usuariosDto.getApellidos());
        usuarios.setUsername(usuariosDto.getUsername());
        usuarios.setEmail(usuariosDto.getEmail());
        usuarios.setTelefono(usuariosDto.getTelefono());
        usuarios.setFoto(usuariosDto.getFoto());
        usuarios.setFecha_nacimiento(usuariosDto.getFecha_nacimiento());
        usuarios.setDireccion(usuariosDto.getDireccion());
        usuarios.setIsProfesional(usuariosDto.getIsProfesional());

        return usuariosRepository.save(usuarios);*/

    }

    @Override
    public Usuarios updatePassword(Integer idUsuario, PasswordDto passworDto) {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(()-> new AppNotFoundException("User not found!"));

        usuario.setPassword(passwordEncoder.encode(passworDto.getPassword()));
        return usuariosRepository.save(usuario);
    }

    @Override
    public UsuariosDto findById(Integer idUsuario) {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(()-> new AppNotFoundException("User not found!"));
        return usuariosMapper.toUsuariosDto(usuario);
    }

    @Override
    public String getTelefono(Integer idUsuario) {
        return usuariosRepository.findTelefonoByIdUsuarios(idUsuario);
    }
}
