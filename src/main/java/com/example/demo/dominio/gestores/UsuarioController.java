package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Usuario;
import com.example.demo.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
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
    public String loginSubmit(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        Optional<Usuario> usuarioOpt = usuarioDAO.findById(usuario.getIdUsuario());
        if (usuarioOpt.isPresent() && usuarioOpt.get().getPass().equals(usuario.getPass())) {
            session.setAttribute("usuario", usuarioOpt.get());
            model.addAttribute("usuario", usuarioOpt.get());
            log.info("Usuario logueado: " + usuarioOpt.get());
            return "redirect:/"; // Redirigir a la página principal después del login
        } else {
            model.addAttribute("error", "ID de usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        return "registro";
    }
}
