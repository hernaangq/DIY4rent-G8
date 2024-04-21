package com.group8.diy4rent.Repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import com.group8.diy4rent.Modelos.Cliente;
import com.group8.diy4rent.Modelos.Alquiler;
import com.group8.diy4rent.Modelos.Herramienta;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {
    List<Alquiler> findByHerramienta(Herramienta herramienta);
}
