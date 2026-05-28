package TallerJakartaEE.ModuloDeClientes.Aplicacion.Interfaz;

import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;

import java.util.List;

public interface ServicioCliente {
    void registrarCliente(Cliente cliente);
    void altaMedioPago(Cliente cliente, MedioDePago medioPago);
    List<Cliente> obtenerClientes();
    void realizarReclamo(Long clienteId, String comentario);
}
