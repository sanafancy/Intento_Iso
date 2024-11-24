package com.example.demo.dominio.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ServicioEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaRecepcion; // Se registra al recoger el pedido
    private LocalDateTime fechaEntrega; // Se registra al entregar el pedido

    @Column(nullable = false)
    private String estado; // Estados: "Notificado", "Recogido", "Entregado"

    @ManyToOne
    @JoinColumn(name = "repartidor_id", nullable = false)
    private Repartidor repartidor;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "direccion_id", nullable = false)
    private Direccion direccion;

    // Constructores
    public ServicioEntrega() {}

    public ServicioEntrega(String estado, Repartidor repartidor, Pedido pedido, Direccion direccion) {
        this.estado = estado;
        this.repartidor = repartidor;
        this.pedido = pedido;
        this.direccion = direccion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return String.format("ServicioEntrega [id=%s, estado=%s, fechaRecepcion=%s, fechaEntrega=%s, repartidor=%s, pedido=%s, direccion=%s]",
                id, estado, fechaRecepcion, fechaEntrega, repartidor.getNombre(), pedido.getId(), direccion);
    }
}
