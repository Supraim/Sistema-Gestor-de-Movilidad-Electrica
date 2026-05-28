package TallerJakartaEE.ModuloDeClientes.Aplicacion.Interfaz;

import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.TipoMedioDePago;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;

import java.util.List;

public interface ServicioCliente {
    void registrarCliente(Cliente cliente);
    void altaMedioPago(Long clienteId, TipoMedioDePago tipo);
    List<Cliente> obtenerClientes();
    void realizarReclamo(Long clienteId, String comentario);
}
