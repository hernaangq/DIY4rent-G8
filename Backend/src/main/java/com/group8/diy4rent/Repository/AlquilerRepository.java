package com.group8.diy4rent.Repository;
import com.group8.diy4rent.Modelos.Alquiler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerRepository extends CrudRepository<Alquiler, Long> {
    
    // Aquí van a ir todas los métodos crud que implementa automáticamente SpringBoot
    // según su nombre (por ejemplo: findByPropietario())
    
}  
