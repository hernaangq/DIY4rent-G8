package com.group8.diy4rent.Security.Modelo;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Modelos.Usuario;

public class PropietarioSecure implements UserDetails {
    private String username;
    private String password;
    private String nombre;
    private String email;

    private Collection<? extends GrantedAuthority> authorities;


    public PropietarioSecure(String username, String password, String nombre, String email, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.authorities = authorities;

    }

    public static PropietarioSecure build(Propietario usuario){

        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol ->
                new SimpleGrantedAuthority(rol.getRolNombre().name())
        ).collect(Collectors.toList());
        return new PropietarioSecure(
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.getNombre(),
            usuario.getEmail(),
            authorities
        );
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
