package TallerJakartaEE.ModuloDeClientes.Interfaces.Evento.Out;

import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEventoClientes {

    @Inject
    private Event<ClienteRegistradoEvento> clienteRegistrado;

    public void publicarClienteRegistrado(Cliente cliente) {
        ClienteRegistradoEvento evento = new ClienteRegistradoEvento(
                cliente.getCedula(),
                cliente.getNombreCompleto()
        );
        clienteRegistrado.fire(evento);
    }
}
