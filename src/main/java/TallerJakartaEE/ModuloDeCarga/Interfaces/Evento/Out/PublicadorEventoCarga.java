package TallerJakartaEE.ModuloDeCarga.Interfaces.Evento.Out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEventoCarga {

    @Inject
    private Event<CargaFinalizadaEvento> cargaFinalizada;

    public void publicarCargaFinalizada(Long clienteId, float importe, float recargo, Long medioDePagoId) {
        CargaFinalizadaEvento evento = new CargaFinalizadaEvento(clienteId, importe, recargo, medioDePagoId);
        cargaFinalizada.fire(evento);
    }
}
