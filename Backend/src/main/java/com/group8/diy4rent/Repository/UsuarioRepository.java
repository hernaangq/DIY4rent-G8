package com.group8.diy4rent.Repository;


import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
// import com.group8.diy4rent.Modelos.Cliente;
import com.group8.diy4rent.Modelos.Usuario;


// @Repository
// public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

// }




public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findBynombre(String nombre);
    Optional<Usuario> findByUsername(String username);
}
