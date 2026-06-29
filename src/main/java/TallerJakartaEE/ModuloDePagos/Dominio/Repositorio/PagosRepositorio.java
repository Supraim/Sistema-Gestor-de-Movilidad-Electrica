package TallerJakartaEE.ModuloDePagos.Dominio.Repositorio;

import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;

import java.time.LocalDateTime;
import java.util.List;

public interface PagosRepositorio {
    void save(MedioDePago medioDePago);
    MedioDePago findById(Long id);
    List<Carga> consultarPagos(Long idCliente, LocalDateTime fechaIni, LocalDateTime fechaFin);
}
