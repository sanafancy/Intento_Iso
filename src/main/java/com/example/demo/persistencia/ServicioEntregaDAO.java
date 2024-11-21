package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.ServicioEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioEntregaDAO extends JpaRepository<ServicioEntrega, Long> {
    // Métodos personalizados pueden ser agregados aquí si es necesario
}
