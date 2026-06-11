package TallerJakartaEE.ModuloDeClientes.Aplicacion.Interfaz;

import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.TipoMedioDePago;

import java.util.List;

public interface ServicioCliente {
    void registrarCliente(Cliente cliente);
    void altaMedioPago(Long clienteId, TipoMedioDePago tipo, String numeroTarjeta,
                      String fechaVencimiento, String digitoVerificador,
                      String tipoTarjeta, String numeroCuenta);
    List<Cliente> obtenerClientes();
    void realizarReclamo(Long clienteId, String comentario);
}
