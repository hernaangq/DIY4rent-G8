package com.group8.diy4rent.Security.Jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.group8.diy4rent.Security.Servicios.PropietarioDetailsServiceImpl;
import com.group8.diy4rent.Security.Servicios.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Esta clase comprueba si el token es valido y si el usuario tiene permisos para acceder al recurso
 * Se ejecuta por cada petición que se haga al servidor
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    /**
     * Implementación de UserDetails específico para Usuario
     */
    @Autowired
    UserDetailsServiceImpl usuarioDetailsService;

    /**
     * Implementación de UserDetails específico para Propietario
     */
    @Autowired
    PropietarioDetailsServiceImpl propietarioDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {
    try {
        String token = getToken(request);

        if (token != null && jwtProvider.validateToken(token)) {
            String nombreUsuario = jwtProvider.getUsernameFromToken(token);

            // Dependiendo de la URL, usará un UserDetails u otro
            UserDetailsService userDetailsService;
            if (request.getRequestURI().endsWith("/loginPropietario")) {
                userDetailsService = propietarioDetailsService;
            } else {
                userDetailsService = usuarioDetailsService;
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    } catch (Exception e) {
    }
    filterChain.doFilter(request, response);
}

    // Para obtener el token de la cabecera
    private String getToken(HttpServletRequest request){

        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;

    }
}