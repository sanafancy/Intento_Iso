package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Pedido;
import com.example.demo.persistencia.PedidoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
public class PedidoController {
    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoDAO pedidoDAO;
/*
    @GetMapping("/buscarPedido")
    public String buscarPedido(@RequestParam(required = false) String busqueda, Model model) {
        List<Pedido> pedidos;
        if (busqueda != null && !busqueda.isEmpty()) {
            // Implementar lógica de búsqueda según los criterios necesarios
            pedidos = pedidoDAO.findAll(); // Placeholder
        } else {
            pedidos = pedidoDAO.findAll();
        }
        model.addAttribute("pedidos", pedidos);
        logger.info("Pedidos encontrados: " + pedidos);
        return "buscarPedido";
    }*/
}
