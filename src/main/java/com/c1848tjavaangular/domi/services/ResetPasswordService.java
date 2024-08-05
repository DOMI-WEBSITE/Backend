package com.c1848tjavaangular.domi.services;

public interface ResetPasswordService {

    public String createPasswordResetToken(String email);

    public boolean validatePasswordResetToken(String token);

    public void resetPassword(String token, String newPassword);
}
