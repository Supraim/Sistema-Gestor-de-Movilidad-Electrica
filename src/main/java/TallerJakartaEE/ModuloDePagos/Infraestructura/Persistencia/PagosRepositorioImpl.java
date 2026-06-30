package TallerJakartaEE.ModuloDePagos.Infraestructura.Persistencia;

import TallerJakartaEE.ModuloDePagos.Dominio.Carga;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;
import TallerJakartaEE.ModuloDePagos.Dominio.Repositorio.PagosRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Carga> consultarPagos(Long idCliente, LocalDateTime fechaIni, LocalDateTime fechaFin){
        try {
            return em.createQuery("SELECT c FROM Carga c WHERE c.cliente.id = :idCliente AND c.fechaInicio >= :fechaIni AND (c.fechaFin <= :fechaFin OR c.fechaFin IS NULL)", Carga.class)
                    .setParameter("idCliente", idCliente)
                    .setParameter("fechaIni", fechaIni)
                    .setParameter("fechaFin", fechaFin)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }
}
