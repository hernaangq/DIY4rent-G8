package com.group8.diy4rent.Repository;
import com.group8.diy4rent.Modelos.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    // Aquí van a ir todas los métodos crud que implementa automáticamente SpringBoot
    // según su nombre (por ejemplo: findByPropietario())

    Optional<Usuario> findByUsername(String nombre);
}  
