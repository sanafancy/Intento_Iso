package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Cliente;
import com.example.demo.persistencia.ClienteDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping("/registro")
    public String showRegistroForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registroCliente";
    }

    @PostMapping("/registro")
    public String registerCliente(@ModelAttribute Cliente cliente) {
        clienteDAO.save(cliente);
        return "redirect:/clientes/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "loginCliente";
    }

    @PostMapping("/login")
    public String loginCliente(@ModelAttribute Cliente cliente, HttpSession session) {
        Optional<Cliente> optionalCliente = clienteDAO.findByEmailAndPass(cliente.getEmail(), cliente.getPass());
        if (optionalCliente.isPresent()) {
            session.setAttribute("cliente", optionalCliente.get());
            return "redirect:/clientes/paginaCliente";
        } else {
            return "loginCliente";
        }
    }

    @GetMapping("/paginaCliente")
    public String paginaCliente() {
        return "paginaCliente";
    }
}