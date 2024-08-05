package com.c1848tjavaangular.domi.repository;

import com.c1848tjavaangular.domi.models.entities.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long> {
    Optional<ResetPassword> findByToken(String token);
}
