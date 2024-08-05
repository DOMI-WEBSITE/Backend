package com.c1848tjavaangular.domi.controllers;

import com.c1848tjavaangular.domi.services.ResetPasswordService;
import com.c1848tjavaangular.domi.services.impl.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class ResetPasswordController {

    private final ResetPasswordService resetPassword;
    private final EmailService emailService;

    @Value("${resetUrl}")
    String resetUrl; // url del reset password

    public ResetPasswordController(ResetPasswordService resetPassword, EmailService emailService) {
        this.resetPassword = resetPassword;
        this.emailService = emailService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        String token = resetPassword.createPasswordResetToken(email);
        String resetPassword =  resetUrl + token;
        emailService.sendSimpleMessage(email, "Password Reset Request", "Haga clic en el enlace para restabecer su contraseña: " + resetPassword);
        return ResponseEntity.ok("Password reset email sent");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        resetPassword.resetPassword(token, newPassword);
        return ResponseEntity.ok().body("Password reset successfully");
    }
}
