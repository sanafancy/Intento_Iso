package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.CartaMenu;
import com.example.demo.persistencia.CartaMenuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class CartaMenuController {
    private static final Logger logger = LoggerFactory.getLogger(CartaMenuController.class);

    @Autowired
    private CartaMenuDAO cartaMenuDAO;

}
