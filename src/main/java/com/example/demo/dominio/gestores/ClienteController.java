package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Cliente;
import com.example.demo.persistencia.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping("/buscarCliente")
    public String buscarCliente(@RequestParam(required = false) String busqueda, Model model) {
        List<Cliente> clientes;
        if (busqueda != null && !busqueda.isEmpty()) {
            clientes = clienteDAO.findByNombreContainingIgnoreCase(busqueda);
        } else {
            clientes = clienteDAO.findAll();
        }
        model.addAttribute("clientes", clientes);
        logger.info("Clientes encontrados: " + clientes);
        return "buscarCliente";
    }
}
