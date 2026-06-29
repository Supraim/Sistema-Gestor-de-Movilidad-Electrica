package TallerJakartaEE.ModuloDePagos.Aplicacion.Interfaz;

import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cliente;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioPagos {

    /**
     * Se invoca cuando finaliza la carga. Busca el medio de pago por ID y
     * cobra al servicio externo correspondiente (tarjeta o cuenta UTE).
     */
    void pagarCarga(Long medioDePagoId, float importe, float recargo);

    List<Carga> consultarPagos(Long idCliente, LocalDateTime inicio, LocalDateTime fin);
}
