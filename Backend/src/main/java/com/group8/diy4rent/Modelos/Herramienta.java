package com.group8.diy4rent.Modelos;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.group8.diy4rent.Enums.EstadoEnum;
import javax.persistence.*;
import javax.validation.constraints.Future;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "HERRAMIENTAS")

public class Herramienta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @JsonIgnore @Lob
    @Column(name = "FOTO")
    private byte[] foto;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO")
    private EstadoEnum estado;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;

    @Future
    @Column(name = "FECHA_FINAL")
    private Date fechaFinal;

    @Column(name = "estaAlquilada")
    private Boolean estaAlquilada;

    
    // Una o varias herramientas pertenecen a un propietario 
    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;

    // Una o varias herramientas pertenecen a un propietario 

    @OneToMany(mappedBy = "herramienta")
    private List<Alquiler> alquileres;


    public Herramienta() {

    }
    
    public Herramienta(Integer id, String nombre, byte[] foto, EstadoEnum estado, Double precio, Date fechaInicio, Date fechaFinal, Propietario propietario, Boolean estaAlquilada) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.estado = estado;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.propietario = propietario; 
        this.estaAlquilada = estaAlquilada;
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

    public byte[] getFoto() {
        return foto;
    }

    @JsonProperty
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Boolean getEstaAlquilada() {
        return estaAlquilada;
    }

    public void setEstaAlquilada(Boolean estaAlquilada) {
        this.estaAlquilada = estaAlquilada;
    }

    public enum Estado {
        COMO_NUEVO,
        MUY_BUENO,
        BUENO,
        ACEPTABLE
    }

}