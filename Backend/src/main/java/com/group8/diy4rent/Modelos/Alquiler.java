package com.group8.diy4rent.Modelos;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ALQUILER")

public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PRECIO_PAGADO")
    private Double precioPagado;

    @Column(name = "ESTRELLA_USUARIO")
    private Integer estrellasUsuario;

    @Column(name = "ESTRELLAS_SERVICIO")
    private Integer estrellasServicio;

    @Column(name = "VALORACION")
    private String valoracion;

    @Column(name = "FECHA_INICIO")
    private Date fechaInicioAlquiler;

    @Column(name = "FECHA_FINAL")
    private Date fechaFinalAlquiler;


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

    public Alquiler(Integer id, Double precioPagado, Integer estrellasUsuario, Integer estrellasServicio, String valoracion, Usuario usuario, Herramienta herramienta, Date fechaInicioAlquiler, Date fechaFinalAlquiler) {
        this.id = id;
        this.precioPagado = precioPagado;
        this.estrellasUsuario = estrellasUsuario;
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

    public Double getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(Double precioPagado) {
        this.precioPagado = precioPagado;
    }

    public Integer getEstrellasUsuario() {
        return estrellasUsuario;
    }

    public void setEstrellasUsuario(Integer estrellasUsuario) {
        this.estrellasUsuario = estrellasUsuario;
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
    
    public Date getFechaInicioAlquiler() {
        return fechaInicioAlquiler;
    }

    public void setFechaInicioAlquiler(Date fechaInicioAlquiler) {
        this.fechaInicioAlquiler = fechaInicioAlquiler;
    }

    public Date getFechaFinalAlquiler() {
        return fechaFinalAlquiler;
    }

    public void setFechaFinalAlquiler(Date fechaFinalAlquiler) {
        this.fechaFinalAlquiler = fechaFinalAlquiler;
    }

}