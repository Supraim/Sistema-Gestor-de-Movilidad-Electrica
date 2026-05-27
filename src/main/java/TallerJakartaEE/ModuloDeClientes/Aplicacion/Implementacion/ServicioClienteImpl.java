package TallerJakartaEE.ModuloDeClientes.Aplicacion.Implementacion;

import TallerJakartaEE.ModuloDeClientes.Aplicacion.Interfaz.ServicioCliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.Reclamo;
import TallerJakartaEE.ModuloDeClientes.Dominio.Repositorio.ClienteRepositorio;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;
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
    public void altaMedioPago(Cliente cliente, MedioDePago medioPago) {
        // TODO: falta implementar asociar medio de pago al cliente
        log.info("Alta medio de pago para cliente: " + cliente.getCedula());
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return repositorio.findAll();
    }

    @Override
    @Transactional
    public void realizarReclamo(Reclamo reclamo) {
        // TODO: falta implementar
    }
}
