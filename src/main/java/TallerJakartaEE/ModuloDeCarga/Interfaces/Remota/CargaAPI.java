package TallerJakartaEE.ModuloDeCarga.Interfaces.Remota;

import TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz.ServicioCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.ClienteComun;
import TallerJakartaEE.ModuloDeClientes.Dominio.ClienteProfesional;
import TallerJakartaEE.ModuloDeClientes.Dominio.TipoProfesion;
import TallerJakartaEE.ModuloDeClientes.Interfaces.Remota.ClienteAPI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Null;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/carga")
public class CargaAPI {

    @Inject
    private ServicioCarga servicioCarga;

    // curl -X POST http://localhost:8080/movilidad-electrica/api/carga/registrarEstacion \
    //   -H "Content-Type: application/json" \
    //   -d '{"descripcion":"Veni a cargar tu auto electrico con nosotros!","calle":"Roman Guerra","departamento":"Maldonado","longitud":33,"latitud":-8}'
    @POST
    @Path("/registrarEstacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarEstacion(CargaRegistroDTO dtoEstacion){
        try{
            EstacionDeCarga estacion;
            estacion = new EstacionDeCarga(null, dtoEstacion.descripcion, dtoEstacion.calle, dtoEstacion.departamento, dtoEstacion.longitud, dtoEstacion.latitud, null);
            servicioCarga.altaEstacion(estacion);
            return Response.ok("Estacion registrada correctamente").build();
        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // DTO
    public static class CargaRegistroDTO {
        public String descripcion;
        public String calle;
        public String departamento;
        public int longitud;
        public int latitud;
    }
}
