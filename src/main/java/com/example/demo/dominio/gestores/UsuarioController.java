package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Usuario;
import com.example.demo.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String autenticarUsuario(@RequestParam String idUsuario, @RequestParam String pass, Model model) {
        Usuario usuario = usuarioDAO.findById(Long.parseLong(idUsuario)).orElse(null);
        if (usuario != null && passwordEncoder.matches(pass, usuario.getPass())) {
            model.addAttribute("usuario", usuario);
            return "bienvenido";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
}