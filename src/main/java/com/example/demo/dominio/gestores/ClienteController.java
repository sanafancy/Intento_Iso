package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Cliente;
import com.example.demo.persistencia.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.PasswordAuthentication;
import java.util.List;

@Controller
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

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
        logger.info("Cliente registrado: " + savedCliente);
        return "resultadoCliente";
    }
}