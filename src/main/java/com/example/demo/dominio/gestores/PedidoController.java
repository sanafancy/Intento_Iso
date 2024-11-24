package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.Pedido;
import com.example.demo.persistencia.PedidoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoDAO pedidoDAO;

    /**
     * Buscar y listar pedidos. Permite filtrar por criterios.
     */
    @GetMapping("/buscar")
    public String buscarPedido(@RequestParam(required = false) String busqueda, Model model) {
        List<Pedido> pedidos;

        if (busqueda != null && !busqueda.isEmpty()) {
            // Implementar lógica de búsqueda basada en 'busqueda' (nombre cliente, estado, etc.)
            pedidos = pedidoDAO.findAll(); // Placeholder para búsqueda específica
        } else {
            pedidos = pedidoDAO.findAll();
        }

        model.addAttribute("pedidos", pedidos);

        // Log adicional para mostrar repartidores asociados a cada pedido
        for (Pedido pedido : pedidos) {
            logger.info(String.format("Pedido ID: %d, Repartidor: %s, Estado: %s",
                    pedido.getId(),
                    pedido.getRepartidor() != null ? pedido.getRepartidor().getNombre() : "No asignado",
                    pedido.getEstado()));
        }

        return "buscarPedido";
    }

    /**
     * Marcar un pedido como recogido.
     */
    @PostMapping("/marcarRecogido")
    @ResponseBody
    public String marcarRecogido(@RequestParam Long id) {
        Pedido pedido = pedidoDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));

        try {
            pedido.marcarComoRecogido();
            pedidoDAO.save(pedido);
            logger.info("Pedido marcado como recogido: ID {}", id);
            return "Pedido marcado como recogido correctamente.";
        } catch (IllegalStateException e) {
            logger.error("Error al marcar pedido como recogido: {}", e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Marcar un pedido como entregado.
     */
    @PostMapping("/marcarEntregado")
    @ResponseBody
    public String marcarEntregado(@RequestParam Long id) {
        Pedido pedido = pedidoDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));

        try {
            pedido.marcarComoEntregado();
            pedidoDAO.save(pedido);
            logger.info("Pedido marcado como entregado: ID {}", id);
            return "Pedido marcado como entregado correctamente.";
        } catch (IllegalStateException e) {
            logger.error("Error al marcar pedido como entregado: {}", e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Mostrar detalles de un pedido específico.
     */
    @GetMapping("/detalle/{id}")
    public String detallePedido(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));

        model.addAttribute("pedido", pedido);
        return "detallePedido";
    }
}
