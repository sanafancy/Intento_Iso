package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Cliente;
import com.example.demo.persistencia.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ClienteController {
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping("/registro/cliente")
    public String registroClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registroCliente";
    }

    @PostMapping("/registro/cliente")
    public String registroClienteSubmit(@ModelAttribute Cliente cliente, Model model) {
        Cliente savedCliente = clienteDAO.save(cliente);
        model.addAttribute("cliente", savedCliente);
        log.info("Cliente registrado: " + savedCliente);
        return "resultadoCliente";
    }
}
