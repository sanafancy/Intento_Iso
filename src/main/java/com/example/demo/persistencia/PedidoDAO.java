package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDAO extends JpaRepository<Pedido, Long> {
}