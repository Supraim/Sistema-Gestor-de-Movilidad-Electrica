package TallerJakartaEE.ModuloDePagos.Interfaces.Evento.Out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEventoPagos {

    @Inject
    private Event<PagoRealizadoEvento> pagoRealizado;

    public void publicarPagoRealizado(String tipoMedioPago, boolean exitoso, float importe) {
        PagoRealizadoEvento evento = new PagoRealizadoEvento(tipoMedioPago, exitoso, importe);
        pagoRealizado.fire(evento);
    }
}
