package com.group8.diy4rent.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group8.diy4rent.Modelos.Rol;
import com.group8.diy4rent.Enums.RolEnum;

public interface RolRepository  extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolEnum rolNombre);

}
