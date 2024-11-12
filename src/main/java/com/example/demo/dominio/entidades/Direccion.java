package com.example.demo.dominio.entidades;

import jakarta.persistence.*;

@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String calle;
    @Column
    private int numero;
    @Column
    private String complemento;
    @Column
    private int codigoPostal;
    @Column
    private String municipio;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    public Direccion() {
    }

    // Constructor
    public Direccion(String calle, int numero, String complemento, int codigoPostal, String municipio) {
        this.calle = calle;
        this.numero = numero;
        this.complemento = complemento;
        this.codigoPostal = codigoPostal;
        this.municipio = municipio;
    }

    // Métodos getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    // Metodo toString
    @Override
    public String toString() {
        return String.format("Direccion [id=%s, calle=%s, numero=%d, complemento=%s, codigoPostal=%d, municipio=%s]", id, calle, numero, complemento, codigoPostal, municipio);
    }
}