package ar.com.practico2.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reclamos")
public class Reclamos {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String asunto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fechahora;
    private boolean finalizado;
    private String mensaje;
    private String observacion;

    @JoinColumn(name = "cliente", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Clientes cliente;

    @JoinColumn(name = "clienteserviciocontrato", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private ClienteServicioContrato clienteserviciocontrato;

    @JoinColumn(name = "reclamosestados", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private ReclamosEstados reclamosestado;

    @JoinColumn(name = "motivosreclamos", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private MotivosReclamos motivosreclamos;

    //@OneToMany(mappedBy = "reclamos", cascade = CascadeType.ALL, orphanRemoval = true)
    //@OrderBy("id")
    //private List<ReclamosRespuesta> reclamosrespuesta;

    //@OneToMany(mappedBy = "reclamos", cascade = CascadeType.ALL, orphanRemoval = true)
    //@OrderBy("id")
    //private List<ReclamosFiles> file;

    //public Reclamos() {
       //reclamosrespuesta = new ArrayList<ReclamosRespuesta>();
       //file = new ArrayList<ReclamosFiles>();
    //}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public ClienteServicioContrato getClienteserviciocontrato() {
        return clienteserviciocontrato;
    }

    public void setClienteserviciocontrato(ClienteServicioContrato clienteserviciocontrato) {
        this.clienteserviciocontrato = clienteserviciocontrato;
    }

    public ReclamosEstados getReclamosestado() {
        return reclamosestado;
    }

    public void setReclamosestado(ReclamosEstados reclamosestado) {
        this.reclamosestado = reclamosestado;
    }

    public MotivosReclamos getMotivosreclamos() {
        return motivosreclamos;
    }

    public void setMotivosreclamos(MotivosReclamos motivosreclamos) {
        this.motivosreclamos = motivosreclamos;
    }

    //public List<ReclamosRespuesta> getReclamosrespuesta() {return reclamosrespuesta;}

    //public void setReclamosrespuesta(List<ReclamosRespuesta> reclamosrespuesta) {this.reclamosrespuesta = reclamosrespuesta;}

}
