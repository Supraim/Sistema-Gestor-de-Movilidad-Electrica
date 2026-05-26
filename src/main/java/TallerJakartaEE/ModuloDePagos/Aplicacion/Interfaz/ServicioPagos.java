package TallerJakartaEE.ModuloDePagos.Aplicacion.Interfaz;

import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioPagos {

    /**
     * Se invoca cuando finaliza la carga. Es el encargado de cobrar la carga utilizando el medio
     * de pago correspondiente (Tarjeta, Factura UTE).
     */
    public void pagarCarga(Cliente cliente, float importe, MedioDePago medioPago);

    /**
     * Retorna la lista de pagos realizados por el cliente. La lista de pagos debería de coincidir
     * con la lista de cargas. La información que devuelva este proceso ayudará a conciliar
     * información.
     */
    public List<Carga> consultarPagos(Cliente cliente, LocalDateTime inicio, LocalDateTime fin);
}
