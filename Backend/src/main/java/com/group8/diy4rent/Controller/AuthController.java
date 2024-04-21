package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Modelos.Usuario;
import com.group8.diy4rent.Repository.HerramientaRepository;
import com.group8.diy4rent.Repository.UsuarioRepository;
import com.group8.diy4rent.Modelos.Alquiler;
import com.group8.diy4rent.Repository.AlquilerRepository;
import com.group8.diy4rent.Security.dto.JwtGenerator;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.beans.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.persistence.*;
import java.util.Date;
import java.util.stream.Collectors;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.authentication.AuthenticationException;



@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    
    
    // Vamos a tener dos rutas: una para el login y otra para el registro

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        // Check for validation errors in the request body
        if (bindingResult.hasErrors()) {
            // Collect all error messages
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
    
            // Return a ResponseEntity with the error messages and a BAD_REQUEST status
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
    
        try {
            // Attempt to authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
    
            // If authentication is successful, set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
    
            // Generate a JWT for the authenticated user
            String jwt = jwtGenerator.generateToken(authentication);
    
            // Retrieve the UserDetails from the Authentication object
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    
            // Create a JwtDto with the JWT, username, and authorities
            JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    
            // Return a ResponseEntity with the JwtDto and an OK status
            return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (AuthenticationException e) {
            // If authentication fails, return a ResponseEntity with an UNAUTHORIZED status
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}
