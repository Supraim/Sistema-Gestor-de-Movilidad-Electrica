package TallerJakartaEE.ModuloDeClientes.Infraestructura.Persistencia;

import TallerJakartaEE.ModuloDeClientes.Dominio.Repositorio.ClienteRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class ClienteRepositorioImpl implements ClienteRepositorio {
    @PersistenceContext
    private EntityManager em;
}
