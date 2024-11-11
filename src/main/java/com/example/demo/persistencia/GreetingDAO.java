package com.example.demo.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.dominio.entidades.*;
@Repository
public interface GreetingDAO extends JpaRepository<Greeting, Long> {
/*
* Se hereda de JpaRepository operaciones de acceso a datos comunes a todas las
entidades:
* E save(E)
* List<E> saveAll(List<E>)
* List<E> findAll()
* E findById(id)
* delete(E)
* deleteById(id)
* ...
*
* Aquí se podrían definir consultas de selección más específicas y complejas
**/
}
