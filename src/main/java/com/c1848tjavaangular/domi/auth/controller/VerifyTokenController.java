package com.c1848tjavaangular.domi.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyTokenController {

    @RequestMapping("/token")
    public String token() {

        return "Hola si funciona el token de acceso!";

    }
}
