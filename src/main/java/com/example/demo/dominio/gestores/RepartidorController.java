package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.*;
import com.example.demo.persistencia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/repartidor")
public class RepartidorController {

    @Autowired
    private RepartidorDAO repartidorDAO;

    @Autowired
    private ServicioEntregaDAO servicioEntregaDAO;

    @Autowired
    private PedidoDAO pedidoDAO;

    /**
     * Ruta para registrar un repartidor.
     */
    @PostMapping("/registrarRepartidor")
    @ResponseBody
    public String registrarRepartidor(@RequestParam String nombre,
                                      @RequestParam String apellidos,
                                      @RequestParam String nif,
                                      @RequestParam List<CodigoPostal> zonas) {
        Repartidor repartidor = new Repartidor();
        repartidor.setNombre(nombre);
        repartidor.setApellidos(apellidos);
        repartidor.setNif(nif);
        repartidor.setZonas(zonas);

        repartidorDAO.save(repartidor);

        return "Repartidor registrado correctamente con nombre: " + nombre + " " + apellidos;
    }

    /**
     * Ruta para marcar un pedido como recogido.
     */
    @PostMapping("/marcarPedidoRecogido")
    @ResponseBody
    public String marcarPedidoRecogido(@RequestParam Long servicioId) {
        ServicioEntrega servicio = servicioEntregaDAO.findById(servicioId)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con ID: " + servicioId));

        Pedido pedido = servicio.getPedido();
        pedido.setEstado("Recogido");
        pedidoDAO.save(pedido);

        servicio.setFechaRecepcion(LocalDateTime.now());
        servicioEntregaDAO.save(servicio);

        return "Pedido marcado como recogido y actualizado correctamente.";
    }

    /**
     * Ruta para marcar un servicio de entrega como entregado.
     */
    @PostMapping("/marcarServicioEntregado")
    @ResponseBody
    public String marcarServicioEntregado(@RequestParam Long servicioId) {
        ServicioEntrega servicio = servicioEntregaDAO.findById(servicioId)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con ID: " + servicioId));

        Pedido pedido = servicio.getPedido();
        pedido.setEstado("Entregado");
        pedidoDAO.save(pedido);

        servicio.setFechaEntrega(LocalDateTime.now());
        servicioEntregaDAO.save(servicio);

        return "Servicio marcado como entregado y actualizado correctamente.";
    }

    /**
     * Ruta para ver los detalles de un servicio de entrega específico.
     */
    @GetMapping("/verServicioEntrega/{servicioId}")
    public String verServicioEntrega(@PathVariable Long servicioId, Model model) {
        ServicioEntrega servicio = servicioEntregaDAO.findById(servicioId)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con ID: " + servicioId));

        model.addAttribute("servicio", servicio);
        return "detalleServicioEntrega";
    }

    @PostMapping("/eliminarRepartidor")
    @ResponseBody
    public String eliminarRepartidor(@RequestParam Long id) {
        Repartidor repartidor = repartidorDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el repartidor con ID: " + id));
        repartidorDAO.delete(repartidor);
        return "Repartidor con ID " + id + " eliminado correctamente.";
    }

}
