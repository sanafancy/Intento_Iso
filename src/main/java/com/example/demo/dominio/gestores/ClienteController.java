package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Cliente;
import com.example.demo.persistencia.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteDAO.findAll();
        model.addAttribute("clientes", clientes);
        logger.info("Clientes encontrados: " + clientes);
        return "listarClientes";
    }

    @GetMapping("/clientes/{id}/restaurantes")
    public String buscarRestaurantes(@PathVariable Long id, Model model) {
        Cliente cliente = clienteDAO.findById(id).orElse(null);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            return "buscarRestaurante";
        }
        return "redirect:/clientes";
    }
}
