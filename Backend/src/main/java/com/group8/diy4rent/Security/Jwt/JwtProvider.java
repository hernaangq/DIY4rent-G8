
package com.group8.diy4rent.Security.Jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;


import java.util.Date;

/**
 * Esta clase se encarga de generar el token, validar el token y obtener el nombre del usuario
 */
@Component
public class JwtProvider {

    // Estos valores se sacan de application.properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    /**
     * Metodo que genera el token
     * setIssuedAt: Fecha de creación del token
     * setExpiration: Fecha de expiración del token
     * signWith: Firma del token
     * compact: Compacta el token
     */
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Para obtener el nombre del usuario a partir del token
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            
        } catch (UnsupportedJwtException e) {
            
        } catch (ExpiredJwtException e) {
            
        } catch (IllegalArgumentException e) {
            
        } catch (SignatureException e) {
            
        }
        return false;
    }
}