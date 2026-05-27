package TallerJakartaEE.ModuloDeClientes.Infraestructura.Persistencia;

import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDeClientes.Dominio.Reclamo;
import TallerJakartaEE.ModuloDeClientes.Dominio.Repositorio.ClienteRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class ClienteRepositorioImpl implements ClienteRepositorio {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public Cliente findByCedula(String cedula) {
        try {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.cedula = :cedula", Cliente.class)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Cliente> findAll() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

    @Override
    public void update(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public void saveReclamo(Reclamo reclamo){
        try{
            em.persist(reclamo);
        }finally{
            em.close();
        }
    }
}
