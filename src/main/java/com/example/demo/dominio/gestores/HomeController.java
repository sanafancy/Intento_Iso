package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Restaurante;
import com.example.demo.persistencia.RestauranteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RestauranteDAO restauranteDAO;

    @GetMapping("/")
    public String home(Model model) {
        List<Restaurante> restaurantes = restauranteDAO.findAll();
        model.addAttribute("restaurantes", restaurantes);
        return "inicio";
    }

    @GetMapping("/buscarRestaurante")
    public String buscarRestaurante(@RequestParam(required = false) String busqueda, Model model) {
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
        return "inicio";
    }

    @GetMapping("/registroOpciones")
    public String registroOpciones() {
        return "registroOpciones";
    }

    @GetMapping("/loginOpciones")
    public String loginOpciones() {
        return "loginOpciones";
    }
}