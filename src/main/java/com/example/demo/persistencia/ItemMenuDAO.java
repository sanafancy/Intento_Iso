package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.CartaMenu;
import com.example.demo.dominio.entidades.ItemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMenuDAO extends JpaRepository<ItemMenu, Long> {
    List<ItemMenu> findByCartaMenu(CartaMenu cartaMenu);
}