package com.example.OlSoftwarePrueba.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PrincipalController {

    @GetMapping("/hello")
    public String hello(){
        return "Hola Mundo";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "Hola Mundo secured";
    }

}
