package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Restaurante;
import com.example.demo.persistencia.RestauranteDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteDAO restauranteDAO;

    @GetMapping("/registro")
    public String showRegistroForm(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "registroRestaurante";
    }

    @PostMapping("/registro")
    public String registerRestaurante(@ModelAttribute Restaurante restaurante) {
        restauranteDAO.save(restaurante);
        return "redirect:/restaurantes/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "loginRestaurante";
    }

    @PostMapping("/login")
    public String loginRestaurante(@ModelAttribute Restaurante restaurante, HttpSession session) {
        Optional<Restaurante> optionalRestaurante = restauranteDAO.findByEmailAndPass(restaurante.getEmail(), restaurante.getPass());
        if (optionalRestaurante.isPresent()) {
            session.setAttribute("restaurante", optionalRestaurante.get());
            return "redirect:/restaurantes/paginaRestaurante";
        } else {
            return "loginRestaurante";
        }
    }

    @GetMapping("/paginaRestaurante")
    public String paginaRestaurante() {
        return "paginaRestaurante";
    }
}