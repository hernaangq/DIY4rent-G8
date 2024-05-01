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
import com.group8.diy4rent.Modelos.Alquiler;
import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Modelos.Usuario;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {
    List<Alquiler> findByHerramienta(Herramienta herramienta);
    List<Alquiler> findByUsuario(Usuario usuario);
}
