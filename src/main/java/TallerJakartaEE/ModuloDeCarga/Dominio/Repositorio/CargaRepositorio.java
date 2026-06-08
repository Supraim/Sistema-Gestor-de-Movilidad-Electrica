package TallerJakartaEE.ModuloDeCarga.Dominio.Repositorio;

import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cliente;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;

import java.time.LocalDateTime;
import java.util.List;

public interface CargaRepositorio {

    void save(Cargador cargador);
    void save(EstacionDeCarga estacion);
    void save(Carga carga);
    EstacionDeCarga findByIdEstacion(Long id);
    Cargador findById(Long id);
    Cliente findClienteById(Long id);
    void updateCliente(Cliente cliente);
    List<EstacionDeCarga> findAll();
    List<Carga> verHistorico(Long idCliente, LocalDateTime fechaIni, LocalDateTime fechaFin);
    Carga verCargaActual(Long idCliente);
}
