package com.group8.diy4rent.Modelos;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


/*INSERT INTO clientes VALUES (1, 'hernan', 'garcia', 'hernan@gmail.com', ST_GeomFromText('POINT(23.3334 -81.4555)'), 'ES1234'); */

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDOS")
    private String apellidos;
    
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;

    @Column(name = "IBAN")
    private String iban;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;


    // private Set<Rol> roles = new HashSet<>();
    public Cliente() {

    }

    public Cliente(String nombre, String apellidos, String email, Double latitud, Double longitud, String iban, String username, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.latitud = latitud;
        this.longitud = longitud;
        this.iban = iban;
        this.username = username;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
    
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
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

    // public Set<Rol> getRoles() {
    //     return roles;
    // }

    // public void setRoles(Set<Rol> roles) {
    //     this.roles = roles;
    // }

}