package com.example.demo.dominio.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Repartidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String nif;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "repartidor_zonas", joinColumns = @JoinColumn(name = "repartidor_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "codigo_postal")
    private List<CodigoPostal> zonas;

    // Constructor vacío
    public Repartidor() {}

    // Constructor completo
    public Repartidor(String nombre, String apellidos, String nif, List<CodigoPostal> zonas) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.zonas = zonas;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public List<CodigoPostal> getZonas() {
        return zonas;
    }

    public void setZonas(List<CodigoPostal> zonas) {
        this.zonas = zonas;
    }

    @Override
    public String toString() {
        return String.format("Repartidor [id=%s, nombre=%s, apellidos=%s, nif=%s, zonas=%s]", id, nombre, apellidos, nif, zonas);
    }
}
