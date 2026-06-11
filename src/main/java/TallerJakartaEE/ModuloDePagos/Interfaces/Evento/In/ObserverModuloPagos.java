package TallerJakartaEE.ModuloDePagos.Interfaces.Evento.In;

import TallerJakartaEE.ModuloDeClientes.Interfaces.Evento.Out.MedioPagoRegistradoEvento;
import TallerJakartaEE.ModuloDePagos.Dominio.CuentaUTE;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;
import TallerJakartaEE.ModuloDePagos.Dominio.Tarjeta;
import TallerJakartaEE.ModuloDePagos.Dominio.TipoTarjeta;
import TallerJakartaEE.ModuloDePagos.Dominio.Repositorio.PagosRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@ApplicationScoped
public class ObserverModuloPagos {
    private static final Logger log = Logger.getLogger(ObserverModuloPagos.class.getName());

    @Inject
    private PagosRepositorio repositorio;

    @Transactional
    public void onMedioPagoRegistrado(@Observes MedioPagoRegistradoEvento evento) {
        log.info("Evento recibido: MedioPagoRegistrado - tipo: " + evento.getTipo()
                + " para cliente ID: " + evento.getClienteId());

        MedioDePago medioDePago;

        if ("TARJETA".equals(evento.getTipo())) {
            TipoTarjeta tipoTarjeta = TipoTarjeta.valueOf(evento.getTipoTarjeta());
            Date fechaVenc = parseFechaVencimiento(evento.getFechaVencimiento());

            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setNumero(evento.getNumeroTarjeta());
            tarjeta.setFechaVencimiento(fechaVenc);
            tarjeta.setDigitoVerificador(evento.getDigitoVerificador());
            tarjeta.setTipo(tipoTarjeta);

            medioDePago = tarjeta;
            log.info("Tarjeta registrada en módulo de Pagos: " + evento.getNumeroTarjeta());

        } else if ("CUENTA_UTE".equals(evento.getTipo())) {
            CuentaUTE cuentaUte = new CuentaUTE();
            cuentaUte.setNumeroCuenta(evento.getNumeroCuenta());

            medioDePago = cuentaUte;
            log.info("Cuenta UTE registrada en módulo de Pagos: " + evento.getNumeroCuenta());

        } else {
            log.warning("Tipo de medio de pago no reconocido: " + evento.getTipo());
            return;
        }

        repositorio.save(medioDePago);
    }

    private Date parseFechaVencimiento(String fecha) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            return sdf.parse(fecha);
        } catch (ParseException e) {
            log.warning("Error al parsear fecha de vencimiento: " + fecha);
            return null;
        }
    }
}
