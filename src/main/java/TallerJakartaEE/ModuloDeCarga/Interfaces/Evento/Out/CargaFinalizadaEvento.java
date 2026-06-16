package TallerJakartaEE.ModuloDeCarga.Interfaces.Evento.Out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CargaFinalizadaEvento {
    private Long clienteId;
    private float importe;
    private float recargo;
    private Long medioDePagoId;
}
