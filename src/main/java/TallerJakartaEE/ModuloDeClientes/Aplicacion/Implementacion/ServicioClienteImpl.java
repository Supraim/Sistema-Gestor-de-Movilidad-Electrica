package TallerJakartaEE.ModuloDeClientes.Aplicacion.Implementacion;

import TallerJakartaEE.ModuloDeClientes.Aplicacion.Interfaz.ServicioCliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.Reclamo;
import TallerJakartaEE.ModuloDeClientes.Dominio.Repositorio.ClienteRepositorio;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ServicioClienteImpl implements ServicioCliente {

    @Inject
    private ClienteRepositorio repositorio;

    @Override
    public void registrarCliente(Cliente cliente) {
        repositorio.saveCliente(cliente);
    }

    @Override
    public void altaMedioPago(Cliente cliente, MedioDePago medioPago) {

    }

    @Override
    public List<Cliente> obtenerClientes() {
        return List.of();
    }

    @Override
    public void realizarReclamo(Reclamo reclamo) {

    }
}
