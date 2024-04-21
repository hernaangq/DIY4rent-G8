package com.group8.diy4rent.Repository;



import java.util.List;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Modelos.Propietario;

@Repository

    public interface HerramientaRepository extends JpaRepository<Herramienta, Integer> {
        List<Herramienta> findByPropietario(Propietario propietario);
    }

