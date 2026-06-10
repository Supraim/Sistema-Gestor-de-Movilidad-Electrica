package TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz;

import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cliente;
// El import de abajo eventualmente se cambiara por un import del modulo actual
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioCarga {
    void iniciarCarga(Long idCliente, Long idCargador);
    Carga verCargaActual(Long idCliente);
    List<Carga> verHistorico(Long idCliente, LocalDateTime inicio, LocalDateTime fin);
    void finalizarCarga(Long idCargador, float consumo, float recargo);
    void altaEstacion(EstacionDeCarga estacion);
    void altaCargador(Cargador cargador);
    List<EstacionDeCarga> obtenerEstaciones();
    EstacionDeCarga findByIdEstacion(Long id);
}
