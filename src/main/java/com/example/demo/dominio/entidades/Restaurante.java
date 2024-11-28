package com.example.demo.dominio.entidades;

import jakarta.persistence.*;
import java.util.List;

// Clase Restaurante mas tarde poner que se extiende del Usuario
@Entity
public class Restaurante extends Usuario{
    @Column
    private String nombre;
    @Column
    private String cif;
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Direccion> direcciones;
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartaMenu> cartasMenu;
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    public Restaurante() {}
    // Constructor
    public Restaurante(String pass,String  nombre, String cif) {
        super(pass);
        this.nombre = nombre;
        this.cif = cif;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }
    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<CartaMenu> getCartasMenu() {
        return cartasMenu;
    }

    public void setCartasMenu(List<CartaMenu> cartasMenu) {
        this.cartasMenu = cartasMenu;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    @Override
    public String toString() {
        return String.format("Restaurante [idUsuario=%s, pass=%s, nombre=%s, cif=%s]", getIdUsuario(), getPass(), nombre, cif);
    }

}