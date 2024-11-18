package com.example.demo.dominio.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cliente extends Usuario{
    @Column
    private String nombre;
    @Column
    private String apellidos;
    @Column
    private String dni;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direcciones;

    public Cliente() {}

    public Cliente(String pass,String nombre, String apellidos, String dni) {
        super (pass);
        this.nombre = nombre;
        this.apellidos=apellidos;
        this.dni=dni;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {
        return String.format("Cliente [idUsuario=%s, pass=%s, nombre=%s, apellidos=%s, dni=%s]", getIdUsuario(), getPass(), nombre, apellidos, dni);
    }
}
