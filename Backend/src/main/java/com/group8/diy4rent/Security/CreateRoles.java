package com.group8.diy4rent.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.group8.diy4rent.Repository.RolRepository;
import com.group8.diy4rent.Modelos.Rol;
import com.group8.diy4rent.Enums.RolEnum;

// Comentar o borrar clase despues del primer run de la aplicación
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol(RolEnum.ROLE_ADMIN);
        Rol rolUser = new Rol(RolEnum.ROLE_USER);
        rolRepository.save(rolAdmin);
        rolRepository.save(rolUser);
    }
}