package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.CartaMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaMenuDAO extends JpaRepository<CartaMenu, Long> {
}