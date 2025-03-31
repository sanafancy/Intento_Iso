package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.CartaMenu;
import com.example.demo.dominio.entidades.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaMenuDAO extends JpaRepository<CartaMenu, Long> {
    List<CartaMenu> findByRestaurante(Restaurante restaurante);
}