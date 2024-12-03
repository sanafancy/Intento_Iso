package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.Cliente;
import com.example.demo.dominio.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}