package com.group8.diy4rent.Modelos;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Alquileres")

public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FECHA_ALQUILADA")
    private Date FechaAlquilada;

    @Column(name = "PRECIO_PAGADO")
    private Double precioPagado;

    @Column(name = "ESTRELLA_USUARIO")
    private Integer estrellaUsuario;

    @Column(name = "ESTRELLAS_SERVICIO")
    private Integer estrellasServicio;

    @Column(name = "VALORACION")
    private String valoracion;

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


    public Alquiler(Long id, Date fechaAlquilada, Double precioPagado, Integer estrellaUsuario,
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


    public Double getPrecioPagado() {
        return precioPagado;
    }


    public void setPrecioPagado(Double precioPagado) {
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