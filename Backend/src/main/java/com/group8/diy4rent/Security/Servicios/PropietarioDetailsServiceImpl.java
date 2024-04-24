package com.group8.diy4rent.Security.Servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Repository.PropietarioRepository;
import com.group8.diy4rent.Security.Modelo.PropietarioSecure;
import com.group8.diy4rent.Security.Modelo.UsuarioSecure;

@Service
public class PropietarioDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PropietarioService propietarioService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Propietario propietario = propietarioService.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return PropietarioSecure.build(propietario);
    }
}