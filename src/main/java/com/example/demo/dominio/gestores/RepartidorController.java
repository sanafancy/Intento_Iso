package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Repartidor;
import com.example.demo.persistencia.RepartidorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RepartidorController {
    private static final Logger logger = LoggerFactory.getLogger(RepartidorController.class);

    @Autowired
    private RepartidorDAO repartidorDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro/repartidor")
    public String mostrarFormularioRegistroRepartidor(Model model) {
        model.addAttribute("repartidor", new Repartidor());
        return "registroRepartidor";
    }

    @PostMapping("/registro/repartidor")
    public String registrarRepartidor(@ModelAttribute Repartidor repartidor, Model model) {
        repartidor.setPass(passwordEncoder.encode(repartidor.getPass()));
        repartidorDAO.save(repartidor);
        model.addAttribute("mensaje", "Repartidor registrado con éxito");
        return "login";
    }
}