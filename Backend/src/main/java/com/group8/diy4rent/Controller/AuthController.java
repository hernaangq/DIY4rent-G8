package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Modelos.Usuario;
import com.group8.diy4rent.Repository.HerramientaRepository;
import com.group8.diy4rent.Repository.PropietarioRepository;
import com.group8.diy4rent.Repository.UsuarioRepository;
import com.group8.diy4rent.Modelos.Alquiler;
import com.group8.diy4rent.Repository.AlquilerRepository;
import com.group8.diy4rent.Security.Jwt.JwtProvider;
import com.group8.diy4rent.Security.dto.JwtDto;
import com.group8.diy4rent.Security.dto.UsuarioLogin;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
import javax.validation.Valid;

import java.util.Date;
import java.util.stream.Collectors;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    // @Autowired
    //  AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("usuarioAuthenticationManager")
    AuthenticationManager usuarioAuthenticationManager;

    @Autowired
    @Qualifier("propietarioAuthenticationManager")
    AuthenticationManager propietarioAuthenticationManager;
    // @Autowired
    //  UserDetailsService userDetailsService;
    @Autowired
     PasswordEncoder passwordEncoder;
    @Autowired
     JwtProvider jwtProvider;
    @Autowired
     UsuarioRepository usuarioRepository;
    @Autowired
    PropietarioRepository propietarioRepository;
    
    // Vamos a tener dos rutas: una para el login y otra para el registro

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UsuarioLogin loginUsuario, BindingResult bindingResult) {
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
            Authentication authentication = usuarioAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
    
            // If authentication is successful, set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
    
            // Generate a JWT for the authenticated user
            String jwt = jwtProvider.generateToken(authentication);
    
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

    @PostMapping("/loginPropietario")
    public ResponseEntity<?> loginPropietario(@Valid @RequestBody UsuarioLogin loginUsuario, BindingResult bindingResult) {
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
            Authentication authentication = propietarioAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
    
            // If authentication is successful, set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
    
            // Generate a JWT for the authenticated user
            String jwt = jwtProvider.generateToken(authentication);
    
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

    @PostMapping("/registroUsuario")
    public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
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
    
        // Check if the username is already taken
        if (usuarioRepository.existsByusername(usuario.getUsername())) {
            // Return a ResponseEntity with an error message and a BAD_REQUEST status
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
    
        // Check if the email is already taken
        if (usuarioRepository.existsByemail(usuario.getEmail())) {
            // Return a ResponseEntity with an error message and a BAD_REQUEST status
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }
    
        // Set the password for the user
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    
        // Save the user to the database
        usuarioRepository.save(usuario);
    
        // Return a ResponseEntity with an OK status
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/registroPropietario")
    public ResponseEntity<?> register(@Valid @RequestBody Propietario propietario, BindingResult bindingResult) {
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
    
        // Check if the username is already taken
        if (propietarioRepository.existsByusername(propietario.getUsername())) {
            // Return a ResponseEntity with an error message and a BAD_REQUEST status
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
    
        // Check if the email is already taken
        if (propietarioRepository.existsByemail(propietario.getEmail())) {
            // Return a ResponseEntity with an error message and a BAD_REQUEST status
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }
    
        // Set the password for the user
        propietario.setPassword(passwordEncoder.encode(propietario.getPassword()));
    
        // Save the user to the database
        propietarioRepository.save(propietario);
    
        // Return a ResponseEntity with an OK status
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
