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
import java.util.Optional;

@Controller
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioDAO usuarioDAO;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Usuario usuario, Model model) {
        Optional<Usuario> usuarioOpt = usuarioDAO.findById(usuario.getIdUsuario());
        if (usuarioOpt.isPresent() && usuarioOpt.get().getPass().equals(usuario.getPass())) {
            model.addAttribute("usuario", usuarioOpt.get());
            logger.info("Usuario logueado: " + usuarioOpt.get());
            return "home"; // Redirigir a la página principal después del login
        } else {
            model.addAttribute("error", "ID de usuario o contraseña incorrectos");
            return "login";
        }
    }
}