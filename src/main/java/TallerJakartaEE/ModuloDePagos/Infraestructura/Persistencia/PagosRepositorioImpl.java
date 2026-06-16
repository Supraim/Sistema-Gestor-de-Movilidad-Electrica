package TallerJakartaEE.ModuloDePagos.Infraestructura.Persistencia;

import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;
import TallerJakartaEE.ModuloDePagos.Dominio.Repositorio.PagosRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class PagosRepositorioImpl implements PagosRepositorio {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(MedioDePago medioDePago) {
        em.persist(medioDePago);
    }

    @Override
    public MedioDePago findById(Long id) {
        return em.find(MedioDePago.class, id);
    }
}
