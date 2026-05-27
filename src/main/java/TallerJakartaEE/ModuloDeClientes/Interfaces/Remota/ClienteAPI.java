package TallerJakartaEE.ModuloDeClientes.Interfaces.Remota;

import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.ClienteComun;
import TallerJakartaEE.ModuloDeClientes.Dominio.ClienteProfesional;
import TallerJakartaEE.ModuloDeClientes.Dominio.Repositorio.ClienteRepositorio;
import TallerJakartaEE.ModuloDeClientes.Dominio.TipoProfesion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/cliente")
public class ClienteAPI {

    @Inject
    private ClienteRepositorio clienteRepositorio;

    // curl -X POST http://localhost:8080/movilidad-electrica/api/cliente/test
    @POST
    @Path("/test")
    @Transactional
    public Response testGuardarCliente() {
        Cliente cli = new ClienteComun("23781242", "Kevin Ramire", "093452395", "1234");
        Cliente cli2 = new ClienteProfesional("13278213", "Pedro el Rayo", "091001034", "12341234",20, TipoProfesion.UBER);
        clienteRepositorio.saveCliente(cli);
        clienteRepositorio.saveCliente(cli2);
        return Response.ok("Clientes guardados correctamente").build();
    }
}
