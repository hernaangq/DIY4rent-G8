package com.group8.diy4rent.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group8.diy4rent.Modelos.Propietario;
import java.util.Optional;


// @Repository
// public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

// }




public interface PropietarioRepository extends JpaRepository<Propietario, Integer> {
    List<Propietario> findBynombre(String nombre);

    Optional<Propietario> findByUsername(String username);
}
