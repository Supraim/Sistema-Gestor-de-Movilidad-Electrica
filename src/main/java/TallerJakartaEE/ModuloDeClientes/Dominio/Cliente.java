package TallerJakartaEE.ModuloDeClientes.Dominio;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "clientes_cliente")
public abstract class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String cedula;
    protected String nombreCompleto;
    protected String telefono;
    protected String contra;

    public Cliente() {}

    public Long getId() { return id; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getContra() { return contra; }
    public void setContra(String contra) { this.contra = contra; }
}
