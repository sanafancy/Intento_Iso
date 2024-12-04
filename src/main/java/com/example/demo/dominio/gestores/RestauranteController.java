package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Restaurante;
import com.example.demo.persistencia.RestauranteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class RestauranteController {
    private static final Logger log = LoggerFactory.getLogger(RestauranteController.class);

    @Autowired
    private RestauranteDAO restauranteDAO;
/*
    @GetMapping("/")
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
        log.info("Restaurantes encontrados: " + restaurantes);
        return "buscarRestaurante";
    }

    @GetMapping("/restaurantes/{restauranteId}/pedido")
    public String listarMenus(@PathVariable Long restauranteId, Model model, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        Restaurante restaurante = restauranteDAO.findById(restauranteId).orElse(null);
        if (restaurante != null) {
            model.addAttribute("restaurante", restaurante);
            model.addAttribute("cartasMenu", restaurante.getCartasMenu());
            log.info("Cartas de menú encontradas para el restaurante: " + restaurante.getNombre());
        }
        return "pedido";
    }*/

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
