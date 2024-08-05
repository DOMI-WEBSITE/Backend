package com.c1848tjavaangular.domi.auth.service;

import com.c1848tjavaangular.domi.exceptions.AppNotFoundException;
import com.c1848tjavaangular.domi.models.entities.Usuarios;
import com.c1848tjavaangular.domi.repository.UsuariosRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailService")
@Transactional(readOnly=true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuariosRepository usuariosRepository;

    public UserDetailsServiceImpl(UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios user = usuariosRepository.findByEmail(email)
                .orElseThrow(() -> new AppNotFoundException("User not found"));
        ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        /*for (Rol rol : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getName()));
        }*/
        return new User(user.getEmail(), user.getPassword(), roles);
    }
}
