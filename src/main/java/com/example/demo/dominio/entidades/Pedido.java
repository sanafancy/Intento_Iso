package com.example.demo.dominio.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private String estado; // Estados posibles: "Pendiente", "Recogido", "En camino", "Entregado"

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "repartidor_id")
    private Repartidor repartidor;

    @ManyToMany
    @JoinTable(
            name = "pedido_item_menu",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "item_menu_id")
    )
    private List<ItemMenu> items;

    // Constructores
    public Pedido() {}

    public Pedido(LocalDateTime fechaHora, String estado, Cliente cliente, Restaurante restaurante, Repartidor repartidor, List<ItemMenu> items) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.cliente = cliente;
        this.restaurante = restaurante;
        this.repartidor = repartidor;
        this.items = items;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public List<ItemMenu> getItems() {
        return items;
    }

    public void setItems(List<ItemMenu> items) {
        this.items = items;
    }

    // Métodos de negocio
    public boolean esEntregable() {
        return "En camino".equals(this.estado);
    }

    public void marcarComoEntregado() {
        if ("En camino".equals(this.estado)) {
            this.estado = "Entregado";
        } else {
            throw new IllegalStateException("El pedido debe estar 'En camino' para ser entregado.");
        }
    }

    public void marcarComoRecogido() {
        if ("Pendiente".equals(this.estado)) {
            this.estado = "En camino";
        } else {
            throw new IllegalStateException("El pedido debe estar 'Pendiente' para ser recogido.");
        }
    }

    @Override
    public String toString() {
        return String.format("Pedido [id=%s, fechaHora=%s, estado=%s, cliente=%s, restaurante=%s, repartidor=%s]",
                id, fechaHora, estado, cliente.getNombre(), restaurante.getNombre(),
                repartidor != null ? repartidor.getNombre() : "Sin asignar");
    }
}
