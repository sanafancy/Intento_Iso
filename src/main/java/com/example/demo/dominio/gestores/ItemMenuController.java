package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.ItemMenu;
import com.example.demo.persistencia.ItemMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class ItemMenuController {
    private static final Logger logger = LoggerFactory.getLogger(ItemMenuController.class);

    @Autowired
    private ItemMenuDAO itemMenuDAO;

    @GetMapping("/buscarItemMenu")
    public String buscarItemMenu(@RequestParam(required = false) String busqueda, Model model) {
        List<ItemMenu> itemsMenu;
        if (busqueda != null && !busqueda.isEmpty()) {
            itemsMenu = itemMenuDAO.findByNombreContainingIgnoreCase(busqueda);
        } else {
            itemsMenu = itemMenuDAO.findAll();
        }
        model.addAttribute("itemsMenu", itemsMenu);
        logger.info("Items de menú encontrados: " + itemsMenu);
        return "buscarItemMenu";
    }
}
