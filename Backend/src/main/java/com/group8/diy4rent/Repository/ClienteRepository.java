package com.group8.diy4rent.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group8.diy4rent.Modelos.Cliente;


// @Repository
// public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

// }




public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findBynombre(String nombre);
    ResponseEntity<Cliente> findByid(Integer id);
}
