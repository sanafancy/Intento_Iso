package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteDAO extends JpaRepository<Restaurante, Long> {
    List<Restaurante> findByNombreContainingIgnoreCase(String nombre);
    List<Restaurante> findByDirecciones_CalleContainingIgnoreCase(String calle);
    List<Restaurante> findByDirecciones_MunicipioContainingIgnoreCase(String municipio);
    List<Restaurante> findByDirecciones_CodigoPostal(int codigoPostal);
    Optional<Restaurante> findByEmailAndPass(String email, String pass);
}