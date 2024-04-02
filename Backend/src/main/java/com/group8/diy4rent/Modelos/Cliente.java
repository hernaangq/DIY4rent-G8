package com.group8.diy4rent.Modelos;
import javax.persistence.*;

@Entity
@Table(name = "Clientes")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDOS")
    private String apellidos;
    
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "COORDENADAS")
    private String coordenadas;

    @Column(name = "IBAN")
    private String iban;

    public Cliente() {

    }

    public Cliente(String nombre, String apellidos, String email, String coordenadas, String iban) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.coordenadas = coordenadas;
        this.iban = iban;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}