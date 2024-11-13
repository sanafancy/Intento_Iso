package com.example.demo.dominio.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime fechaHora;
    @Column
    private String estado;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
    @JoinTable(
            name = "pedido_item_menu",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "item_menu_id")
    )
    private List<ItemMenu> items;
    /*@ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;*/

    public Pedido(){}
    public Pedido(LocalDateTime fechaHora, String estado, Cliente cliente, Restaurante restaurante, List<ItemMenu> items/*,Direccion direccion*/){
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.cliente = cliente;
        this.restaurante = restaurante;
        this.items = items;
        //this.direccion = direccion;
    }

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

    public List<ItemMenu> getItems() {
        return items;
    }

    public void setItems(List<ItemMenu> items) {
        this.items = items;
    }
    /*public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }*/
//poner , direccion=%s
    @Override
    public String toString() {
        return String.format("Pedido [id=%s, fechaHora=%s, estado=%s, cliente=%s, restaurante=%s]", id, fechaHora, estado, cliente, restaurante);
    }
}