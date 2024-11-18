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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/registro/cliente")
    public String mostrarFormularioRegistroCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registroCliente";
    }

    @PostMapping("/registro/cliente")
    public String registrarCliente(@ModelAttribute Cliente cliente, Model model) {
        cliente.setPass(passwordEncoder.encode(cliente.getPass()));
        clienteDAO.save(cliente);
        model.addAttribute("mensaje", "Cliente registrado con éxito");
        return "login";
    }
}