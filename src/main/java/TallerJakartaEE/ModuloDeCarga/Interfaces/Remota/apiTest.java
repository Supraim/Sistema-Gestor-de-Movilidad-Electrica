package TallerJakartaEE.ModuloDeCarga.Interfaces.Remota;
import TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz.ServicioCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.*;
import TallerJakartaEE.ModuloDeCarga.Infraestructura.RateLimiter.RateLimited;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



@Path("/test")
public class apiTest {

    @GET
    @PermitAll
    public String test() {
        return "OK";
    }
    @Inject
    jakarta.ws.rs.core.SecurityContext ctx;

    @GET
    @Path("/test")
    public String testo() {

        System.out.println("USER: " + ctx.getUserPrincipal());
        System.out.println("ROLE mobil: " + ctx.isUserInRole("mobil"));

        return "ok";
    }



}
