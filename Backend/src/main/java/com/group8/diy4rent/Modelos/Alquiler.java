package com.group8.diy4rent.Modelos;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "alquileres")

public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaAlquilada")
    @Temporal(TemporalType.Date)
    private Date FechaAlquilada;

    @Column(name = "precioPagado")
    private Integer precioPagado;

    @Column(name = "estrellaUsuario")
    private Integer estrellaUsuario;

    @Column(name = "estrellasServicio")
    private Integer estrellasServicio;

    @Column(name = "valoracion")
    private String valoracion;


    public Alquiler() {

    }


    public Alquiler(Long id, Date fechaAlquilada, Integer precioPagado, Integer estrellaUsuario,
            Integer estrellasServicio, String valoracion) {
        this.id = id;
        FechaAlquilada = fechaAlquilada;
        this.precioPagado = precioPagado;
        this.estrellaUsuario = estrellaUsuario;
        this.estrellasServicio = estrellasServicio;
        this.valoracion = valoracion;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Date getFechaAlquilada() {
        return FechaAlquilada;
    }


    public void setFechaAlquilada(Date fechaAlquilada) {
        FechaAlquilada = fechaAlquilada;
    }


    public Integer getPrecioPagado() {
        return precioPagado;
    }


    public void setPrecioPagado(Integer precioPagado) {
        this.precioPagado = precioPagado;
    }


    public Integer getEstrellaUsuario() {
        return estrellaUsuario;
    }


    public void setEstrellaUsuario(Integer estrellaUsuario) {
        this.estrellaUsuario = estrellaUsuario;
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

    

}