package TallerJakartaEE.ModuloDePagos.Interfaces.Evento.Out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagoRealizadoEvento {
    private String tipoMedioPago;   // "TARJETA" o "CUENTA_UTE"
    private boolean exitoso;
    private float importe;
}
