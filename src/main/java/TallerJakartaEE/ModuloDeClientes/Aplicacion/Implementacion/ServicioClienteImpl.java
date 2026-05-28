package TallerJakartaEE.ModuloDeClientes.Aplicacion.Implementacion;

import TallerJakartaEE.ModuloDeClientes.Aplicacion.Interfaz.ServicioCliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.MedioDePago;
import TallerJakartaEE.ModuloDeClientes.Dominio.Reclamo;
import TallerJakartaEE.ModuloDeClientes.Dominio.Repositorio.ClienteRepositorio;
import TallerJakartaEE.ModuloDeClientes.Dominio.TipoMedioDePago;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ServicioClienteImpl implements ServicioCliente {
    private static final Logger log = Logger.getLogger(ServicioClienteImpl.class.getName());

    @Inject
    private ClienteRepositorio repositorio;

    @Override
    @Transactional
    public void registrarCliente(Cliente cliente) {
        Cliente existente = repositorio.findByCedula(cliente.getCedula());
        if (existente != null) {
            throw new IllegalArgumentException("Ya existe un cliente con la cédula: " + cliente.getCedula());
        }

        log.info("Registrando cliente: " + cliente.getNombreCompleto());
        repositorio.save(cliente);
    }

    @Override
    @Transactional
    public void altaMedioPago(Long clienteId, TipoMedioDePago tipo) {
        // Falta implementar la parte de asociar el medio de pago en el Modulo de Pagos, en dicha tabla
        // esto lo haremos a traves de eventos.
        // Actualmente registramos y asociamos un medio de pago con el cliente, pero no tenemos guardada
        // la informacion de dicho de medio de pago en la tabla correspondiente del Modulo de Pagos
        // ya sea CUENTA_UTE o TARJETA.
        Cliente cliente = repositorio.findById(clienteId);

        if (cliente == null) {
            throw new IllegalArgumentException("No existe un cliente con el ID: " + clienteId);
        }
        MedioDePago medioDePago = new MedioDePago(tipo, cliente);

        repositorio.asociarMedioDePago(medioDePago);

        log.info("Alta medio de pago para cliente: " + cliente.getCedula());
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return repositorio.findAll();
    }

    @Override
    @Transactional
    public void realizarReclamo(Long clienteId, String comentario) {
        Cliente cliente = repositorio.findById(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("No existe un cliente con el ID: " + clienteId);
        }

        Reclamo reclamo = new Reclamo(comentario, LocalDateTime.now(), cliente);
        cliente.registrarReclamo(reclamo);

        log.info("Registrando reclamo para cliente: " + cliente.getNombreCompleto());
        repositorio.saveReclamo(reclamo);
    }
}
