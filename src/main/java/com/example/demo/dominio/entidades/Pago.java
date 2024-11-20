package com.example.demo.dominio.entidades;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idTransaccion;
    @Column
    private Date fechaTransaccion;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
    @OneToOne(mappedBy = "pago")
    private Pedido pedido;

    public UUID getIdTransaccion() {
        return idTransaccion;
    }
    public void setIdTransaccion(UUID idTransaccion) {
        this.idTransaccion = idTransaccion;
    }
    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }
    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
