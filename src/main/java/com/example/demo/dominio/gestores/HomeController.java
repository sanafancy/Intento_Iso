package com.example.demo.dominio.gestores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "inicio";
    }

    @GetMapping("/registroOpciones")
    public String registroOpciones() {
        return "registroOpciones";
    }

    @GetMapping("/loginOpciones")
    public String loginOpciones() {
        return "loginOpciones";
    }
}