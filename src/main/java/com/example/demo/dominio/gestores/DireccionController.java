package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Direccion;
import com.example.demo.persistencia.DireccionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class DireccionController {

    private static final Logger log = LoggerFactory.getLogger(DireccionController.class);

    @Autowired
    private DireccionDAO direccionDAO;

}
