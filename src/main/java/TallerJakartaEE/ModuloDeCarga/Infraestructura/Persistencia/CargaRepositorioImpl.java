package TallerJakartaEE.ModuloDeCarga.Infraestructura.Persistencia;

import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Repositorio.CargaRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class CargaRepositorioImpl implements CargaRepositorio {

    @PersistenceContext
    private EntityManager em;

    public void save(Cargador cargador){ em.persist(cargador); }

    public void save(EstacionDeCarga estacion){ em.persist(estacion); }

    public Cargador findById(Long id){ return em.find(Cargador.class, id); }

    public List<EstacionDeCarga> findAll(){
        return em.createQuery("SELECT ec FROM EstacionDeCarga ec", EstacionDeCarga.class)
                .getResultList();
    }

    public EstacionDeCarga findByIdEstacion(Long id){
        return em.find(EstacionDeCarga.class, id);
    }

    public List<Carga> verHistorico(Long idCliente, LocalDateTime fechaIni, LocalDateTime fechaFin){
        try {
            return em.createQuery("SELECT c FROM Carga c WHERE c.idCliente = :idCliente AND c.horaInicio >= :fechaIni AND c.horaFin <= :fechaFin", Carga.class)
                    .setParameter("idCliente", idCliente)
                    .setParameter("fechaIni", fechaIni)
                    .setParameter("fechaFin", fechaFin)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    public Carga verCargaActual (Long idCliente){
        try {
            return em.createQuery("SELECT c FROM Carga c WHERE c.idCliente = :idCliente AND c.estado = 'CARGANDO' ", Carga.class)
                    .setParameter("idCliente", idCliente)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }


}
