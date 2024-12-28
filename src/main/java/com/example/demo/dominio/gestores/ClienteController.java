package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Cliente;
import com.example.demo.persistencia.ClienteDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public String showLoginForm(@RequestParam(value = "redirectUrl", required = false) String redirectUrl, Model model) {
        model.addAttribute("redirectUrl", redirectUrl);  // Pasar la URL de redirección al formulario
        return "loginCliente";  // Vista del formulario de inicio de sesión
    }
    /*public String showLoginForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "loginCliente";
    }*/

    @PostMapping("/login")
    public String loginCliente(@ModelAttribute Cliente cliente, HttpSession session) {
        Optional<Cliente> optionalCliente = clienteDAO.findByEmailAndPass(cliente.getEmail(), cliente.getPass());
        if (optionalCliente.isPresent()) {
            session.setAttribute("cliente", optionalCliente.get());
            return "redirect:/";  // Siempre redirige a la página de búsqueda
        } else {
            return "loginCliente";
        }
    }
    /*public String loginCliente(@ModelAttribute Cliente cliente, HttpSession session,
                               @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {
        Optional<Cliente> optionalCliente = clienteDAO.findByEmailAndPass(cliente.getEmail(), cliente.getPass());
        if (optionalCliente.isPresent()) {
            session.setAttribute("cliente", optionalCliente.get());
            // Si hay un redirectUrl, redirige allí después del login
            if (redirectUrl != null && !redirectUrl.isEmpty()) {
                return "redirect:" + redirectUrl;
            }
            return "redirect:/";  // Si no hay redirectUrl, ve a la página de inicio
        } else {
            return "loginCliente";
        }
    }*/ //redirigir a pagina de restaurante

    @GetMapping("/paginaCliente")
    public String paginaCliente() {
        return "paginaCliente";
    }
}