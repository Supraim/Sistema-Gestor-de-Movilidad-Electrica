package TallerJakartaEE.ModuloDeClientes.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes_cliente_profesional")
public class ClienteProfesional extends Cliente {

    private float porcentajeDescuento;

    @Enumerated(EnumType.STRING)
    private TipoProfesion tipo;

    public ClienteProfesional() {}

    public ClienteProfesional(String cedula, String nombreCompleto, String telefono, String contra, float porcentajeDescuento, TipoProfesion tipo) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.contra = contra;
        this.porcentajeDescuento = porcentajeDescuento;
        this.tipo = tipo;
    }

    public float getPorcentajeDescuento() { return porcentajeDescuento; }
    public void setPorcentajeDescuento(float porcentajeDescuento) { this.porcentajeDescuento = porcentajeDescuento; }
    public TipoProfesion getTipo() { return tipo; }
    public void setTipo(TipoProfesion tipo) { this.tipo = tipo; }
}
