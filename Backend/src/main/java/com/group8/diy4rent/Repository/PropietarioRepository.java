package com.group8.diy4rent.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group8.diy4rent.Modelos.Propietario;


// @Repository
// public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

// }




public interface PropietarioRepository extends JpaRepository<Propietario, Integer> {
    List<Propietario> findBynombre(String nombre);
    Optional<Propietario> findByusername(String username);
    ResponseEntity<Propietario> findByid(Integer id);
    Boolean existsByusername(String username);
    Boolean existsByemail(String email);
}
