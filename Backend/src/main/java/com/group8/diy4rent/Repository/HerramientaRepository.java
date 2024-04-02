package com.group8.diy4rent.Repository;
import com.group8.diy4rent.Modelos.Herramienta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HerramientaRepository extends CrudRepository<Herramienta, Long> {

    // Aquí van a ir todas los métodos crud que implementa automáticamente SpringBoot
    // según su nombre (por ejemplo: findByPropietario())
    
    List<Herramienta> findByPropietarioId(Long id);
    List<Herramienta> findByNombre(String nombre);
}