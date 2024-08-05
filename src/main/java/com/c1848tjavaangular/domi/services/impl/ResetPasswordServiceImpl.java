package com.c1848tjavaangular.domi.services.impl;

import com.c1848tjavaangular.domi.exceptions.AppNotFoundException;
import com.c1848tjavaangular.domi.models.entities.ResetPassword;
import com.c1848tjavaangular.domi.models.entities.Usuarios;
import com.c1848tjavaangular.domi.repository.ResetPasswordRepository;
import com.c1848tjavaangular.domi.repository.UsuariosRepository;
import com.c1848tjavaangular.domi.services.ResetPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final UsuariosRepository usuariosRepository;
    private final ResetPasswordRepository resetPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordServiceImpl(UsuariosRepository usuariosRepository, ResetPasswordRepository resetPasswordRepository, PasswordEncoder passwordEncoder){
        this.usuariosRepository = usuariosRepository;
        this.resetPasswordRepository = resetPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createPasswordResetToken(String email) {
        Usuarios usuario = usuariosRepository.findByEmail(email)
                .orElseThrow(()-> new AppNotFoundException("Usuario not found!"));

        String token = UUID.randomUUID().toString();
        ResetPassword myToken = new ResetPassword(token, usuario);
        resetPasswordRepository.save(myToken);

        return token;
    }

    @Override
    public boolean validatePasswordResetToken(String token) {
        ResetPassword myToken = resetPasswordRepository.findByToken(token).orElse(null);
        return myToken != null && !myToken.isExpired();
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        if (validatePasswordResetToken(token)) {
            ResetPassword myToken = resetPasswordRepository.findByToken(token)
                    .orElseThrow(() -> new AppNotFoundException("Token invalido!"));

            Usuarios usuario = myToken.getUser();
            usuario.setPassword(passwordEncoder.encode(newPassword));
            usuariosRepository.save(usuario);
        } else {
            throw new AppNotFoundException("Token invalido o ha expirado");
        }

    }
}
