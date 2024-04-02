package com.group8.diy4rent.Repository;
import com.group8.diy4rent.Modelos.Propietario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PropietarioRepository extends CrudRepository<Propietario, Long> {

    // Aquí van a ir todas los métodos crud que implementa automáticamente SpringBoot
    // según su nombre (por ejemplo: findByPropietario())

    Optional<Propietario> findByUsername(String nombre);
}  

