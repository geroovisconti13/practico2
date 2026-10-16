package ar.com.practico2.entities;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "clienteserviciocontrato")
public class ClienteServicioContrato {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaalta;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechabaja;

    @JoinColumn(name = "cliente", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Clientes cliente;

    @JoinColumn(name = "servicio", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Servicios servicio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }
}
