package TallerJakartaEE.ModuloDeClientes.Aplicacion.Interfaz;

import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.Reclamo;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;

import java.util.List;

public interface ServicioCliente {
    public void registrarCliente(Cliente cliente);
    public void altaMedioPago(Cliente cliente, MedioDePago medioPago);
    public List<Cliente> obtenerClientes();
    public void realizarReclamo(Reclamo reclamo);
}
