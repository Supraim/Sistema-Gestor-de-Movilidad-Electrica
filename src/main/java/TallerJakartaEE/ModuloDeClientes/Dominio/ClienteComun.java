package TallerJakartaEE.ModuloDeClientes.Dominio;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "clientes_cliente_comun")
public class ClienteComun extends Cliente {

    public ClienteComun(String cedula, String nombreCompleto, String telefono, String contra) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.contra = contra;
    }
}
