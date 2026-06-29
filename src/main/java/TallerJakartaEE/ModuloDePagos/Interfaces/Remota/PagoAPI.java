package TallerJakartaEE.ModuloDePagos.Interfaces.Remota;

import TallerJakartaEE.ModuloDeCarga.Infraestructura.RateLimiter.RateLimited;
import TallerJakartaEE.ModuloDePagos.Aplicacion.Interfaz.ServicioPagos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/pago")
public class PagoAPI {

    @Inject
    private ServicioPagos servicioPagos;

    @GET
    @Path("/ConsultarPagos")
    @RateLimited
    @Produces(MediaType.APPLICATION_JSON)
    public Response verHistoricoDeCargas(){ return null;}
}
