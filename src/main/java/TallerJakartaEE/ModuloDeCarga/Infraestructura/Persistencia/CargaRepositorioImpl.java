package TallerJakartaEE.ModuloDeCarga.Infraestructura.Persistencia;

import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Repositorio.CargaRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class CargaRepositorioImpl implements CargaRepositorio {

    @PersistenceContext
    private EntityManager em;

    public void save(Cargador cargador){ em.persist(cargador); }

    public void save(EstacionDeCarga estacion){ em.persist(estacion); }
}
