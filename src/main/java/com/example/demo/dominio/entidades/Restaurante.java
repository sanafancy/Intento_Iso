package com.example.demo.dominio.entidades;

import jakarta.persistence.*;
import java.util.List;

// Clase Restaurante mas tarde poner que se extiende del Usuario
@Entity
public class Restaurante{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String cif;
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;
    public Restaurante() {}
    // Constructor
    public Restaurante(String nombre, String cif) {
        this.nombre = nombre;
        this.cif = cif;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    @Override public String toString() {
        return String.format("Restaurante [id=%s, nombre=%s, cif=%s]", id, nombre, cif);
    }
}