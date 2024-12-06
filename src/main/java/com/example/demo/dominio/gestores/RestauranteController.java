package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.CartaMenu;
import com.example.demo.dominio.entidades.ItemMenu;
import com.example.demo.dominio.entidades.Restaurante;
import com.example.demo.persistencia.CartaMenuDAO;
import com.example.demo.persistencia.ItemMenuDAO;
import com.example.demo.persistencia.RestauranteDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteDAO restauranteDAO;

    @Autowired
    private CartaMenuDAO cartaMenuDAO;

    @Autowired
    private ItemMenuDAO itemMenuDAO;

    //registrarse
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

    //iniciar sesion
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

    //pagina principal de restaurante
    @GetMapping("/paginaRestaurante")
    public String paginaRestaurante() {
        return "paginaRestaurante";
    }

    //carta y las funciones del menu
    @GetMapping("/carta")
    public String carta(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<CartaMenu> cartas = cartaMenuDAO.findByRestaurante(restaurante);
            model.addAttribute("cartas", cartas);

            // Obtener los ítems de cada menú
            Map<Long, List<ItemMenu>> itemsPorMenu = new HashMap<>();
            for (CartaMenu carta : cartas) {
                List<ItemMenu> items = itemMenuDAO.findByCartaMenu(carta);
                itemsPorMenu.put(carta.getId(), items);
            }
            model.addAttribute("itemsPorMenu", itemsPorMenu);
        }
        return "carta";
    }

    @GetMapping("/anadirMenu")
    public String showAnadirMenuForm(Model model) {
        model.addAttribute("cartaMenu", new CartaMenu());
        return "anadirMenu";
    }

    @PostMapping("/anadirMenu")
    public String anadirMenu(@ModelAttribute CartaMenu cartaMenu, HttpSession session) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            cartaMenu.setRestaurante(restaurante);
            cartaMenuDAO.save(cartaMenu);
        }
        return "redirect:/restaurantes/carta";
    }
    @GetMapping("/eliminarMenu")
    public String showEliminarMenuPage(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<CartaMenu> cartas = cartaMenuDAO.findByRestaurante(restaurante);
            model.addAttribute("cartas", cartas);
        }
        return "eliminarMenu";
    }

    // Procesar la eliminación de menús seleccionados
    @PostMapping("/eliminarMenu")
    public String eliminarMenus(@RequestParam("menuIds") List<Long> menuIds) {
        for (Long id : menuIds) {
            cartaMenuDAO.deleteById(id);
        }
        return "redirect:/restaurantes/carta";
    }
    // Añadir metodo para mostrar la página de todos los menús
    @GetMapping("/todosMenus")
    public String showTodosMenus(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<CartaMenu> cartas = cartaMenuDAO.findByRestaurante(restaurante);
            model.addAttribute("cartas", cartas);
        }
        return "todosMenus";
    }

    // Añadir metodo para mostrar el formulario de añadir ítem
    @GetMapping("/anadirItem/{menuId}")
    public String showAnadirItemForm(@PathVariable Long menuId, Model model) {
        model.addAttribute("itemMenu", new ItemMenu());
        model.addAttribute("menuId", menuId);
        return "anadirItem";
    }

    // Añadir metodo para manejar la lógica de añadir ítem
    @PostMapping("/anadirItem")
    public String anadirItem(@ModelAttribute ItemMenu itemMenu, @RequestParam Long menuId) {
        Optional<CartaMenu> optionalCartaMenu = cartaMenuDAO.findById(menuId);
        if (optionalCartaMenu.isPresent()) {
            itemMenu.setCartaMenu(optionalCartaMenu.get());
            itemMenuDAO.save(itemMenu);
        }
        return "redirect:/restaurantes/carta";
    }

    @GetMapping("/eliminarItems")
    public String showEliminarItemsPage(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<CartaMenu> cartas = cartaMenuDAO.findByRestaurante(restaurante);
            model.addAttribute("cartas", cartas);
            // Obtener los ítems de cada menú
            Map<Long, List<ItemMenu>> itemsPorMenu = new HashMap<>();
            for (CartaMenu carta : cartas) {
                List<ItemMenu> items = itemMenuDAO.findByCartaMenu(carta);
                itemsPorMenu.put(carta.getId(), items);
            }
            model.addAttribute("itemsPorMenu", itemsPorMenu);
        }
        return "eliminarItems";
    }
    @PostMapping("/eliminarItems")
    public String eliminarItems(@RequestParam("itemIds") List<Long> itemIds) {
        for (Long id : itemIds) {
            itemMenuDAO.deleteById(id);
        }
        return "redirect:/restaurantes/carta";
    }
}