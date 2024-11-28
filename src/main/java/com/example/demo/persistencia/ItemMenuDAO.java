package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.ItemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMenuDAO extends JpaRepository<ItemMenu, Long> {
}