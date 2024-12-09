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
/*
    @GetMapping("/buscarDireccion")
    public String buscarDireccion(@RequestParam(required = false) String calle,
                                  @RequestParam(required = false) String municipio,
                                  @RequestParam(required = false) Integer codigoPostal,
                                  Model model) {
        List<Direccion> direcciones;
        if (calle != null && !calle.isEmpty()) {
            direcciones = direccionDAO.findByCalleContainingIgnoreCase(calle);
        } else if (municipio != null && !municipio.isEmpty()) {
            direcciones = direccionDAO.findByMunicipioContainingIgnoreCase(municipio);
        } else if (codigoPostal != null) {
            direcciones = direccionDAO.findByCodigoPostal(codigoPostal);
        } else {
            direcciones = direccionDAO.findAll();
        }
        model.addAttribute("direcciones", direcciones);
        log.info("Direcciones encontradas: " + direcciones);
        return "buscarDireccion";
    }*/
}
