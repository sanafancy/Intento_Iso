package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Repartidor;
import com.example.demo.persistencia.RepartidorDAO;
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
@RequestMapping("/repartidores")
public class RepartidorController {
    @Autowired
    private RepartidorDAO repartidorDAO;

    @GetMapping("/registro")
    public String showRegistroForm(Model model) {
        model.addAttribute("repartidor", new Repartidor());
        return "registroRepartidor";
    }

    @PostMapping("/registro")
    public String registerRepartidor(@ModelAttribute Repartidor repartidor) {
        repartidorDAO.save(repartidor);
        return "redirect:/repartidores/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("repartidor", new Repartidor());
        return "loginRepartidor";
    }

    @PostMapping("/login")
    public String loginRepartidor(@ModelAttribute Repartidor repartidor, HttpSession session) {
        Optional<Repartidor> optionalRepartidor = repartidorDAO.findByEmailAndPass(repartidor.getEmail(), repartidor.getPass());
        if (optionalRepartidor.isPresent()) {
            session.setAttribute("repartidor", optionalRepartidor.get());
            return "redirect:/repartidores/paginaRepartidor";
        } else {
            return "loginRepartidor";
        }
    }

    @GetMapping("/paginaRepartidor")
    public String paginaRepartidor() {
        return "paginaRepartidor";
    }
}