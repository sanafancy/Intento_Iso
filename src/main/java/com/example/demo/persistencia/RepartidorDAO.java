package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartidorDAO extends JpaRepository<Repartidor, Long> {
    // Método personalizado para buscar un repartidor por su NIF
    Repartidor findByNif(String nif);
}
