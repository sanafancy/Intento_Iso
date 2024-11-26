package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Usuario;
import com.example.demo.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Controller
@SessionAttributes("usuarioLogueado")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

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
            model.addAttribute("usuarioLogueado", usuarioOpt.get());
            log.info("Usuario logueado: " + usuarioOpt.get());
            return "redirect:/Inicio"; // Redirigir a la página principal después del login
        } else {
            model.addAttribute("error", "ID de usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        return "registro";
    }
}