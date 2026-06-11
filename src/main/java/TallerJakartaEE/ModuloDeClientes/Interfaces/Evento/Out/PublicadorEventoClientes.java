package TallerJakartaEE.ModuloDeClientes.Interfaces.Evento.Out;

import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEventoClientes {

    @Inject
    private Event<ClienteRegistradoEvento> clienteRegistrado;

    @Inject
    private Event<MedioPagoRegistradoEvento> medioPagoRegistrado;

    public void publicarClienteRegistrado(Cliente cliente) {
        ClienteRegistradoEvento evento = new ClienteRegistradoEvento(
                cliente.getCedula(),
                cliente.getNombreCompleto()
        );
        clienteRegistrado.fire(evento);
    }

    public void publicarMedioPagoRegistrado(Long clienteId, String tipo, String numeroTarjeta,
                                            String fechaVencimiento, String digitoVerificador,
                                            String tipoTarjeta, String numeroCuenta) {
        MedioPagoRegistradoEvento evento = new MedioPagoRegistradoEvento(
                clienteId, tipo, numeroTarjeta, fechaVencimiento, digitoVerificador,
                tipoTarjeta, numeroCuenta
        );
        medioPagoRegistrado.fire(evento);
    }
}
