package com.group8.diy4rent.Security.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Modelos.Usuario;
import com.group8.diy4rent.Repository.PropietarioRepository;
import com.group8.diy4rent.Repository.UsuarioRepository;

@Service
public class PropietarioService {
    @Autowired
    private PropietarioRepository propietarioRepository;

    public Optional<Propietario> getByUsername(String username) {
        return propietarioRepository.findByusername(username);
    }
}
