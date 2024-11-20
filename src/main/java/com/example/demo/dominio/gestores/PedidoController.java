package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.MetodoPago;
import com.example.demo.dominio.entidades.Pago;
import com.example.demo.dominio.entidades.Pedido;
import com.example.demo.persistencia.PagoDAO;
import com.example.demo.persistencia.PedidoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class PedidoController {
    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoDAO pedidoDAO;
    @Autowired
    private PagoDAO pagoDAO;

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
    }
    @GetMapping("/realizarPago")
    public String realizarPago(@RequestParam Long pedidoId, @RequestParam MetodoPago metodoPago, Model model) {
        Pedido pedido = pedidoDAO.findById(pedidoId).orElse(null);
        if (pedido != null) {
            Pago pago = new Pago();
            pago.setIdTransaccion(UUID.randomUUID());
            pago.setFechaTransaccion(new Date());
            pago.setMetodoPago(metodoPago);
            pago.setPedido(pedido);
            pagoDAO.save(pago);
            model.addAttribute("mensaje", "Pago realizado con éxito");
            logger.info("Pago realizado: " + pago);
        } else {
            model.addAttribute("mensaje", "Pedido no encontrado");
            logger.warn("Pedido no encontrado: " + pedidoId);
        }
        return "resultadoPago";
    }
}
