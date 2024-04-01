package com.group8.diy4rent.Modelos;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "HERRAMIENTA")

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
    private Estado estado;

    @Column(name = "PRECIO")
    private Boolean precio;

    @Column(name = "ESTA_ALQUILADA")
    private Boolean estaAlquilada;

    @Column(name = "FECHAS_DISPONIBLES")
    private Date fechasDisponibles;

    public Herramienta() {

    }
    
    public Herramienta(Long id, String nombre, byte[] foto, Estado estado, Boolean precio, Boolean estaAlquilada, Date fechasDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.estado = estado;
        this.precio = precio;
        this.estaAlquilada = estaAlquilada;
        this.fechasDisponibles = fechasDisponibles;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Boolean getPrecio() {
        return precio;
    }

    public void setPrecio(Boolean precio) {
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

    public enum Estado {
        COMO_NUEVO,
        MUY_BUENO,
        BUENO,
        ACEPTABLE
    }

}