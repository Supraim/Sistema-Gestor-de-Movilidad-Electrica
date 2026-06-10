package TallerJakartaEE.ModuloDeClientes.Interfaces.Evento.Out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteRegistradoEvento {
    private String cedula;
    private String nombreCompleto;
}
