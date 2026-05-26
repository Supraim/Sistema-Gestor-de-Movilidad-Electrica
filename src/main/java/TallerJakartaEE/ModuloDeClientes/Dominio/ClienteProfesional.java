package TallerJakartaEE.ModuloDeClientes.Dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes_cliente_profesional")
public class ClienteProfesional extends Cliente {

    private float porcentajeDescuento;

    @Enumerated(EnumType.STRING)
    private TipoProfesion tipo;

    public ClienteProfesional(String cedula, String nombreCompleto, String telefono, String contra, float porcentajeDescuento, TipoProfesion tipo) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.contra = contra;
        this.porcentajeDescuento = porcentajeDescuento;
        this.tipo = tipo;
    }
}
