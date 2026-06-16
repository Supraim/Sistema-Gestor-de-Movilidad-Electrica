package TallerJakartaEE.ModuloDeCarga.Interfaces.Evento.Out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CargaIniciadaEvento {
    private Long clienteId;
    private Long cargadorId;
}
