package com.example.demo.persistencia;
import com.example.demo.dominio.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoDAO extends JpaRepository<Pago, Long>{

}
