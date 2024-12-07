package com.example.demo.persistencia;

import com.example.demo.dominio.entidades.Direccion;
import com.example.demo.dominio.entidades.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionDAO extends JpaRepository<Direccion, Long> {
    /*List<Direccion> findByCalleContainingIgnoreCase(String calle);
    List<Direccion> findByMunicipioContainingIgnoreCase(String municipio);
    List<Direccion> findByCodigoPostal(int codigoPostal);*/
    List<Direccion> findByRestaurante(Restaurante restaurante);
}
