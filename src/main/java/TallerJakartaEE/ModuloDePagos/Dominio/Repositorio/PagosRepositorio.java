package TallerJakartaEE.ModuloDePagos.Dominio.Repositorio;

import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;

public interface PagosRepositorio {
    void save(MedioDePago medioDePago);
}
