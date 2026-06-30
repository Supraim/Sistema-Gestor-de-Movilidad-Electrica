package TallerJakartaEE.ModuloDePagos.Aplicacion.Implementacion;

import TallerJakartaEE.ModuloDePagos.Aplicacion.Interfaz.ServicioPagos;
import TallerJakartaEE.ModuloDePagos.Dominio.Carga;
import TallerJakartaEE.ModuloDePagos.Dominio.CuentaUTE;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;
import TallerJakartaEE.ModuloDePagos.Dominio.Tarjeta;
import TallerJakartaEE.ModuloDePagos.Dominio.Repositorio.PagosRepositorio;
import TallerJakartaEE.ModuloDePagos.Interfaces.Evento.Out.PublicadorEventoPagos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ServicioPagosImpl implements ServicioPagos {
    private static final Logger log = Logger.getLogger(ServicioPagosImpl.class.getName());

    private static final String URL_MOCK_TARJETA = "http://localhost:8080/MockMedioDePago/api/pagos/procesar";
    private static final String URL_MOCK_UTE = "http://localhost:8080/MockPagoCuentaUTE/api/pagos/cuenta-ute";

    @Inject
    private PagosRepositorio repositorio;

    @Inject
    private PublicadorEventoPagos publicadorEvento;

    @Override
    @Transactional
    public void pagarCarga(Long medioDePagoId, float importe, float recargo) {
        MedioDePago medioDePago = repositorio.findById(medioDePagoId);
        if (medioDePago == null) {
            log.warning("No se encontró medio de pago con ID: " + medioDePagoId);
            return;
        }

        float total = importe + recargo;

        if (medioDePago instanceof Tarjeta tarjeta) {
            cobrarConTarjeta(tarjeta, total);
        } else if (medioDePago instanceof CuentaUTE cuentaUte) {
            cobrarConCuentaUTE(cuentaUte, total);
        } else {
            log.warning("Tipo de medio de pago no soportado para cobro");
        }
    }

    private void cobrarConTarjeta(Tarjeta tarjeta, float total) {
        log.info("Cobrando con tarjeta: " + tarjeta.getNumero() + " | Importe: " + total);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String fechaVenc = tarjeta.getFechaVencimiento() != null ? sdf.format(tarjeta.getFechaVencimiento()) : "";

        String json = String.format(
                "{\"numeroTarjeta\":\"%s\",\"fechaVencimiento\":\"%s\",\"digitoVerificador\":\"%s\",\"tipoTarjeta\":\"%s\",\"importe\":%s}",
                tarjeta.getNumero(),
                fechaVenc,
                tarjeta.getDigitoVerificador(),
                tarjeta.getTipo().name(),
                total
        );

        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(URL_MOCK_TARJETA)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(json));

            String respuesta = response.readEntity(String.class);
            log.info("Respuesta mock tarjeta: " + respuesta);

            boolean exitoso = respuesta.contains("\"aprobado\":true");
            publicadorEvento.publicarPagoRealizado("TARJETA", exitoso, total);
        } catch (Exception e) {
            log.warning("Error al comunicarse con mock de tarjeta: " + e.getMessage());
            publicadorEvento.publicarPagoRealizado("TARJETA", false, total);
        }
    }

    private void cobrarConCuentaUTE(CuentaUTE cuentaUte, float total) {
        log.info("Cobrando con cuenta UTE: " + cuentaUte.getNumeroCuenta() + " | Importe: " + total);

        String json = String.format(
                "{\"numeroCuenta\":\"%s\",\"monto\":%s}",
                cuentaUte.getNumeroCuenta(),
                total
        );

        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target(URL_MOCK_UTE)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(json));

            String respuesta = response.readEntity(String.class);
            log.info("Respuesta mock UTE: " + respuesta);

            publicadorEvento.publicarPagoRealizado("CUENTA_UTE", true, total);
        } catch (Exception e) {
            log.warning("Error al comunicarse con mock de UTE: " + e.getMessage());
            publicadorEvento.publicarPagoRealizado("CUENTA_UTE", false, total);
        }
    }

    @Override
    public List<Carga> consultarPagos(Long idCliente, LocalDateTime inicio, LocalDateTime fin) {
        return repositorio.consultarPagos(idCliente, inicio, fin);
    }
}
