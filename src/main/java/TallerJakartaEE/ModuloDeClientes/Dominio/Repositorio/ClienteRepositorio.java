package TallerJakartaEE.ModuloDeClientes.Dominio.Repositorio;

import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;

import java.util.List;

public interface ClienteRepositorio {
    void save(Cliente cliente);
    Cliente findById(Long id);
    Cliente findByCedula(String cedula);
    List<Cliente> findAll();
    void update(Cliente cliente);
}
