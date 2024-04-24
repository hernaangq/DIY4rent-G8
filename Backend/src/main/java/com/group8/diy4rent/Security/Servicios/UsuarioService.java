package com.group8.diy4rent.Security.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group8.diy4rent.Modelos.Usuario;
import com.group8.diy4rent.Repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByUsername(String username) {
        return usuarioRepository.findByusername(username);
    }
}
