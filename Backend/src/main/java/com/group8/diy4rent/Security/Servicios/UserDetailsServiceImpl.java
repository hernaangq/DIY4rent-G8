package com.group8.diy4rent.Security.Servicios;


import com.group8.diy4rent.Modelos.Usuario;
import com.group8.diy4rent.Repository.UsuarioRepository;
import com.group8.diy4rent.Security.Modelo.UsuarioSecure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Clase que convierte la clase usuario en un UsuarioMain
 * UserDetailsService es propia de Spring Security
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByUsername(username).get();
        return UsuarioSecure.build(usuario);
    }
}
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     Usuario usuario = usuarioRepository.findByusername(username).orElseThrow(
//         () -> new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username));
// return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
// }