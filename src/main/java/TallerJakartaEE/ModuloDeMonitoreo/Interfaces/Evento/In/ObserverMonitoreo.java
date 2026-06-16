package TallerJakartaEE.ModuloDeMonitoreo.Interfaces.Evento.In;

import TallerJakartaEE.ModuloDeCarga.Interfaces.Evento.Out.CargaFinalizadaEvento;
import TallerJakartaEE.ModuloDeCarga.Interfaces.Evento.Out.CargaIniciadaEvento;
import TallerJakartaEE.ModuloDePagos.Interfaces.Evento.Out.PagoRealizadoEvento;
import TallerJakartaEE.ModuloDeMonitoreo.Infraestructura.RegistradorDeMetricas;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.util.logging.Logger;

@ApplicationScoped
public class ObserverMonitoreo {
    private static final Logger log = Logger.getLogger(ObserverMonitoreo.class.getName());

    @Inject
    private RegistradorDeMetricas registrador;

    public void onCargaIniciada(@Observes CargaIniciadaEvento evento) {
        log.info("Monitoreo: Carga iniciada - clienteId: " + evento.getClienteId());
        registrador.incrementarCounter(RegistradorDeMetricas.CARGAS_ACTIVAS);
    }

    public void onCargaFinalizada(@Observes CargaFinalizadaEvento evento) {
        log.info("Monitoreo: Carga finalizada - clienteId: " + evento.getClienteId());
        registrador.incrementarCounter(RegistradorDeMetricas.CARGAS_REALIZADAS);
    }

    public void onPagoRealizado(@Observes PagoRealizadoEvento evento) {
        log.info("Monitoreo: Pago realizado - tipo: " + evento.getTipoMedioPago()
                + " | exitoso: " + evento.isExitoso());

        if ("TARJETA".equals(evento.getTipoMedioPago())) {
            if (evento.isExitoso()) {
                registrador.incrementarCounter(RegistradorDeMetricas.PAGOS_TARJETA);
            } else {
                registrador.incrementarCounter(RegistradorDeMetricas.ERROR_PAGO_TARJETA);
            }
        } else if ("CUENTA_UTE".equals(evento.getTipoMedioPago())) {
            registrador.incrementarCounter(RegistradorDeMetricas.PAGOS_UTE);
        }
    }
}
