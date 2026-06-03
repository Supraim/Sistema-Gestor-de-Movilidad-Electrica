package TallerJakartaEE.ModuloDeCarga.Dominio.Repositorio;

import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;

import java.util.List;

public interface CargaRepositorio {

    void save(Cargador cargador);
    void save(EstacionDeCarga estacion);
    EstacionDeCarga findByIdEstacion(Long id);
    List<EstacionDeCarga> findAll();
}
