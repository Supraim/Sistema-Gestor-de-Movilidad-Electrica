package TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz;

import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cliente;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioCarga {
    void iniciarCarga(Cliente cliente, MedioDePago medioDePago);
    Carga verCargaActual(Cliente cliente);
    List<Carga> verHistorico(Long idCliente, LocalDateTime inicio, LocalDateTime fin);
    void finalizarCarga(Cargador cargador, Carga carga, LocalDateTime recargo);
    void altaEstacion(EstacionDeCarga estacion);
    void altaCargador(Cargador cargador);
    List<EstacionDeCarga> obtenerEstaciones();
    EstacionDeCarga findByIdEstacion(Long id);
}
