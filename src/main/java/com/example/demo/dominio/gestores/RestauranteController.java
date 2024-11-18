package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Restaurante;
import com.example.demo.persistencia.RestauranteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class RestauranteController {
    private static final Logger logger = LoggerFactory.getLogger(RestauranteController.class);

    @Autowired
    private RestauranteDAO restauranteDAO;

    @GetMapping("/registro/restaurante")
    public String registroRestauranteForm(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "registroRestaurante";
    }

    @PostMapping("/registro/restaurante")
    public String registroRestauranteSubmit(@ModelAttribute Restaurante restaurante, Model model) {
        Restaurante savedRestaurante = restauranteDAO.save(restaurante);
        model.addAttribute("restaurante", savedRestaurante);
        logger.info("Restaurante registrado: " + savedRestaurante);
        return "resultadoRestaurante";
    }

    @GetMapping("/restaurantes")
    public String listarRestaurantes(@RequestParam(required = false) String busqueda, Model model) {
        List<Restaurante> restaurantes;
        if (busqueda != null && !busqueda.isEmpty()) {
            restaurantes = restauranteDAO.findByNombreContainingIgnoreCase(busqueda);
            restaurantes.addAll(restauranteDAO.findByDirecciones_CalleContainingIgnoreCase(busqueda));
            restaurantes.addAll(restauranteDAO.findByDirecciones_MunicipioContainingIgnoreCase(busqueda));
            try {
                int codigoPostal = Integer.parseInt(busqueda);
                restaurantes.addAll(restauranteDAO.findByDirecciones_CodigoPostal(codigoPostal));
            } catch (NumberFormatException e) {
                // No es un código postal válido, ignorar
            }
        } else {
            restaurantes = restauranteDAO.findAll();
        }
        model.addAttribute("restaurantes", restaurantes);
        logger.info("Restaurantes encontrados: " + restaurantes);
        return "buscarRestaurante";
    }

    @GetMapping("/restaurantes/{restauranteId}/pedido")
    public String listarMenus(@PathVariable Long restauranteId, Model model) {
        Restaurante restaurante = restauranteDAO.findById(restauranteId).orElse(null);
        if (restaurante != null) {
            model.addAttribute("restaurante", restaurante);
            model.addAttribute("cartasMenu", restaurante.getCartasMenu());
            logger.info("Cartas de menú encontradas para el restaurante: " + restaurante.getNombre());
        }
        return "pedido";
    }
}
