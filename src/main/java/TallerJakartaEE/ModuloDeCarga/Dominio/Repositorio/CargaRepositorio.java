package TallerJakartaEE.ModuloDeCarga.Dominio.Repositorio;

import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;

public interface CargaRepositorio {

    void save(Cargador cargador);
    void save(EstacionDeCarga estacion);
}
