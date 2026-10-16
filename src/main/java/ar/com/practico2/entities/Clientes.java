package ar.com.practico2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Clientes {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String apellido;
    private String direccion;
    private String email;
    private String nombre;
    private String nrodoc;
    private String telefono;

    @JoinColumn(name = "tipodoc", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private TiposDoc tipodoc;

    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Usuarios usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNrodoc() {
        return nrodoc;
    }

    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TiposDoc getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(TiposDoc tipodoc) {
        this.tipodoc = tipodoc;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
