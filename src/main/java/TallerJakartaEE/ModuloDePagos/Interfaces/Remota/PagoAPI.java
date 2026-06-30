package TallerJakartaEE.ModuloDePagos.Interfaces.Remota;

import TallerJakartaEE.ModuloDePagos.Dominio.Carga;
import TallerJakartaEE.ModuloDePagos.Aplicacion.Interfaz.ServicioPagos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
@Path("/pago")
public class PagoAPI {

    @Inject
    private ServicioPagos servicioPagos;

    // curl "http://localhost:8080/movilidad-electrica/api/pago/consultarPagos?idCliente=1&fechaInicio=2020-01-01%2000:00:00&fechaFin=2030-12-31%2023:59:59"
    @GET
    @Path("/consultarPagos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPagos(
            @QueryParam("idCliente") Long idCliente,
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin){

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime fInicio = LocalDateTime.parse(fechaInicio, formatter);
        LocalDateTime fFin = LocalDateTime.parse(fechaFin, formatter);

        List<Carga> cargasCliente = servicioPagos.consultarPagos(idCliente, fInicio, fFin);
        return Response.ok(cargasCliente).build();
    }
}
