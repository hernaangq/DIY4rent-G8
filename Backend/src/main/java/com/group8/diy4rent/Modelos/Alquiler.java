package com.group8.diy4rent.Modelos;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "ALQUILER")

public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ESTRELLAS_SERVICIO")
    private Integer estrellasServicio;

    @Column(name = "VALORACION")
    private String valoracion;

    @Column(name = "FECHA_INICIO")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fechaInicioAlquiler;

    @Column(name = "FECHA_FINAL")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fechaFinalAlquiler;


    // Un alquiler está asociado a un usuario único (one) y varios alquileres pueden estar asociados a un usuario (many)
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Un alquiler está asociado a una herramienta única que se alquila (one) y varios alquileres pueden estar asociados a una herramienta (many)

    @ManyToOne
    @JoinColumn(name = "herramienta_id")
    private Herramienta herramienta;


    public Alquiler() {

    }

    public Alquiler(Integer id, Integer estrellasUsuario, Integer estrellasServicio, String valoracion, Usuario usuario, Herramienta herramienta, LocalDate fechaInicioAlquiler, LocalDate fechaFinalAlquiler) {
        this.id = id;
        this.estrellasServicio = estrellasServicio;
        this.valoracion = valoracion;
        this.usuario = usuario;
        this.herramienta = herramienta;
        this.fechaInicioAlquiler = fechaInicioAlquiler;
        this.fechaFinalAlquiler = fechaFinalAlquiler;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getEstrellasServicio() {
        return estrellasServicio;
    }

    public void setEstrellasServicio(Integer estrellasServicio) {
        this.estrellasServicio = estrellasServicio;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
    }
    
    public LocalDate getFechaInicioAlquiler() {
        return fechaInicioAlquiler;
    }

    public void setFechaInicioAlquiler(LocalDate fechaInicioAlquiler) {
        this.fechaInicioAlquiler = fechaInicioAlquiler;
    }

    public LocalDate getFechaFinalAlquiler() {
        return fechaFinalAlquiler;
    }

    public void setFechaFinalAlquiler(LocalDate fechaFinalAlquiler) {
        this.fechaFinalAlquiler = fechaFinalAlquiler;
    }

}