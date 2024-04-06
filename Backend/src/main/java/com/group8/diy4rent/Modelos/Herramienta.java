package com.group8.diy4rent.Modelos;

import com.group8.diy4rent.Enums.EstadoEnum;
import javax.persistence.*;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "HERRAMIENTAS")

public class Herramienta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Lob
    @Column(name = "FOTO")
    private byte[] foto;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO")
    private EstadoEnum estado;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "ESTA_ALQUILADA")
    private Boolean estaAlquilada;

    @Column(name = "FECHAS_DISPONIBLES")
    private Date fechasDisponibles;

    // Una o varias herramientas pertenecen a un propietario 
    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;

    // Una o varias herramientas pertenecen a un propietario 

    @OneToMany(mappedBy = "herramienta")
    private List<Alquiler> alquileres;


    public Herramienta() {

    }
    
    public Herramienta(Long id, String nombre, byte[] foto, EstadoEnum estado, Double precio, Boolean estaAlquilada, Date fechasDisponibles, Propietario propietario) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.estado = estado;
        this.precio = precio;
        this.estaAlquilada = estaAlquilada;
        this.fechasDisponibles = fechasDisponibles;
        this.propietario = propietario;
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

    public byte[] getFoto() {
        return foto;
    }

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

    public Boolean getEstaAlquilada() {
        return estaAlquilada;
    }

    public void setEstaAlquilada(Boolean estaAlquilada) {
        this.estaAlquilada = estaAlquilada;
    }

    public Date getFechasDisponibles() {
        return fechasDisponibles;
    }

    public void setFechasDisponibles(Date fechasDisponibles) {
        this.fechasDisponibles = fechasDisponibles;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    

}