package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Repartidor;
import com.example.demo.persistencia.RepartidorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class RepartidorController {
    private static final Logger logger = LoggerFactory.getLogger(RepartidorController.class);

    @Autowired
    private RepartidorDAO repartidorDAO;

    @GetMapping("/listarRepartidores")
    public String listarRepartidores(Model model) {
        List<Repartidor> repartidores = repartidorDAO.findAll();
        model.addAttribute("repartidores", repartidores);
        logger.info("Repartidores listados: " + repartidores);
        return "listarRepartidores";
    }

    @GetMapping("/buscarRepartidor")
    public String buscarRepartidor(@RequestParam(required = false) String nif, Model model) {
        if (nif != null && !nif.isEmpty()) {
            Repartidor repartidor = repartidorDAO.findByNif(nif);
            if (repartidor != null) {
                model.addAttribute("repartidor", repartidor);
                logger.info("Repartidor encontrado: " + repartidor);
            } else {
                logger.warn("No se encontró repartidor con NIF: " + nif);
                model.addAttribute("mensaje", "No se encontró repartidor con el NIF proporcionado");
            }
        } else {
            model.addAttribute("mensaje", "Debe proporcionar un NIF para la búsqueda");
            logger.warn("Búsqueda de repartidor sin NIF proporcionado");
        }
        return "buscarRepartidor";
    }
}
