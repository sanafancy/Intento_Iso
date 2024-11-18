package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Repartidor;
import com.example.demo.persistencia.RepartidorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class RepartidorController {
    private static final Logger log = LoggerFactory.getLogger(RepartidorController.class);

    @Autowired
    private RepartidorDAO repartidorDAO;

    @GetMapping("/registro/repartidor")
    public String registroRepartidorForm(Model model) {
        model.addAttribute("repartidor", new Repartidor());
        return "registroRepartidor";
    }

    @PostMapping("/registro/repartidor")
    public String registroRepartidorSubmit(@ModelAttribute Repartidor repartidor, Model model) {
        Repartidor savedRepartidor = repartidorDAO.save(repartidor);
        model.addAttribute("repartidor", savedRepartidor);
        log.info("Repartidor registrado: " + savedRepartidor);
        return "resultadoRepartidor";
    }
}