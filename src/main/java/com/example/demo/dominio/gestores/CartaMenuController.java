package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.CartaMenu;
import com.example.demo.persistencia.CartaMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class CartaMenuController {
    private static final Logger logger = LoggerFactory.getLogger(CartaMenuController.class);

    @Autowired
    private CartaMenuDAO cartaMenuDAO;

    @GetMapping("/menus")
    public String listarMenus(Model model) {
        List<CartaMenu> cartasMenu = cartaMenuDAO.findAll();
        model.addAttribute("cartasMenu", cartasMenu);
        logger.info("Cartas de menú encontradas: " + cartasMenu);
        return "listarMenus";
    }

    @GetMapping("/menus/{id}")
    public String verItemsMenu(@PathVariable Long id, Model model) {
        CartaMenu cartaMenu = cartaMenuDAO.findById(id).orElse(null);
        if (cartaMenu != null) {
            model.addAttribute("cartaMenu", cartaMenu);
            model.addAttribute("itemsMenu", cartaMenu.getItems());
            logger.info("Items de menú para la carta: " + cartaMenu.getNombre());
        }
        return "verItemsMenu";
    }
}
