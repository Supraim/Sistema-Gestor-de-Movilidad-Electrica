package TallerJakartaEE.ModuloDeClientes.Interfaces.Remota;

import TallerJakartaEE.ModuloDeClientes.Aplicacion.Interfaz.ServicioCliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.ClienteComun;
import TallerJakartaEE.ModuloDeClientes.Dominio.ClienteProfesional;
import TallerJakartaEE.ModuloDeClientes.Dominio.TipoProfesion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/cliente")
public class ClienteAPI {

    @Inject
    private ServicioCliente servicioCliente;

    // curl -X POST http://localhost:8080/movilidad-electrica/api/cliente/registrar \
    //   -H "Content-Type: application/json" \
    //   -d '{"cedula":"12345678","nombreCompleto":"Juan Perez","telefono":"099123456","contra":"pass123","esProfesional":false}'
    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarCliente(ClienteRegistroDTO dto) {
        try {
            Cliente cliente;
            if (dto.esProfesional) {
                TipoProfesion tipo = TipoProfesion.valueOf(dto.tipoProfesion);
                cliente = new ClienteProfesional(dto.cedula, dto.nombreCompleto,
                        dto.telefono, dto.contra, dto.porcentajeDescuento, tipo);
            } else {
                cliente = new ClienteComun(dto.cedula, dto.nombreCompleto,
                        dto.telefono, dto.contra);
            }

            servicioCliente.registrarCliente(cliente);
            return Response.ok("Cliente registrado correctamente").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // curl -X POST http://localhost:8080/movilidad-electrica/api/cliente/registrarReclamo \
    //   -H "Content-Type: application/json" \
    //   -d '{"comentario":"El cobre que me vendieron es una caca","idCliente":"1"}'
    @POST
    @Path("/registrarReclamo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarReclamo(ReclamoRegistroDTO dtoRec){
        try{
            servicioCliente.realizarReclamo(dtoRec.idCliente, dtoRec.comentario);
            return Response.ok("Reclamo registrado correctamente").build();
        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // curl http://localhost:8080/movilidad-electrica/api/cliente/todos
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerClientes() {
        List<Cliente> clientes = servicioCliente.obtenerClientes();
        return Response.ok(clientes).build();
    }

    // DTO
    public static class ClienteRegistroDTO {
        public String cedula;
        public String nombreCompleto;
        public String telefono;
        public String contra;
        public boolean esProfesional;
        public String tipoProfesion;      // "TAXI", "UBER", "CABIFY"
        public float porcentajeDescuento;
    }

    public static class ReclamoRegistroDTO{
        public String comentario;
        public Long idCliente;
    }
}
