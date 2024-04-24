package com.group8.diy4rent.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
// import com.group8.diy4rent.Modelos.Cliente;
import com.group8.diy4rent.Modelos.Usuario;


// @Repository
// public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

// }




public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findBynombre(String nombre);
    Optional<Usuario> findByusername(String username);
    ResponseEntity<Usuario> findByid(Integer id);
    Boolean existsByusername(String username);
    Boolean existsByemail(String email);
}
