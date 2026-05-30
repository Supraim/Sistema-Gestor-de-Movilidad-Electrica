package TallerJakartaEE.ModuloDePagos.Aplicacion.Implementacion;

import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDePagos.Aplicacion.Interfaz.ServicioPagos;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public class ServicioPagosImpl implements ServicioPagos {

    @Override
    @Transactional
    public void pagarCarga(Cliente cliente, float importe, MedioDePago medioPago) {

    }

    @Override
    public List<Carga> consultarPagos(Cliente cliente, LocalDateTime inicio, LocalDateTime fin) {
        return List.of();
    }
}
