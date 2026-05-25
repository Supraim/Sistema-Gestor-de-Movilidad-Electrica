package TallerJakartaEE.ModuloDeClientes.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes_cliente_comun")
public class ClienteComun extends Cliente {

    public ClienteComun() {}

    public ClienteComun(String cedula, String nombreCompleto, String telefono, String contra) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.contra = contra;
    }
}
