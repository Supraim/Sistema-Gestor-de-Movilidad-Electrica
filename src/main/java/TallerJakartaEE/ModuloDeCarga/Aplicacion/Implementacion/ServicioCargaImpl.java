package TallerJakartaEE.ModuloDeCarga.Aplicacion.Implementacion;

import TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz.ServicioCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstadoCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstadoCargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.Repositorio.CargaRepositorio;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ServicioCargaImpl implements ServicioCarga {
    private static final Logger log = Logger.getLogger(ServicioCargaImpl.class.getName());

    @Inject
    private CargaRepositorio repositorio;

    @Override
    @Transactional
    public void iniciarCarga(Long idCliente, Long idCargador) {
        Cliente cliente = repositorio.findClienteById(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("No existe un cliente con el ID: " + idCliente);
        }

        if (cliente.getCargaActiva() != null) {
            throw new IllegalArgumentException("El cliente ya tiene una carga activa");
        }

        Cargador cargador = repositorio.findById(idCargador);
        if (cargador == null) {
            throw new IllegalArgumentException("No existe un cargador con el ID: " + idCargador);
        }

        if (cargador.getEstado() != EstadoCargador.DISPONIBLE) {
            throw new IllegalArgumentException("El cargador no está disponible");
        }

        // Crear la carga
        Carga carga = new Carga();
        carga.setCliente(cliente);
        carga.setHoraInicio(LocalDateTime.now());
        carga.setFecha(new java.util.Date());
        carga.setEstado(EstadoCarga.CARGANDO);
        carga.setPorcentajeAvance(0);

        repositorio.save(carga);

        // Asociar al cliente
        cliente.iniciarCarga(carga);
        repositorio.updateCliente(cliente);

        // Marcar cargador en uso
        cargador.setEstado(EstadoCargador.EN_USO);

        log.info("Carga iniciada para cliente: " + cliente.getNombreCompleto() + " en cargador: " + idCargador);
    }

    @Override
    public Carga verCargaActual(Cliente cliente) {
        return null;
    }

    @Override
    public List<Carga> verHistorico(Long idCliente, LocalDateTime inicio, LocalDateTime fin) {
        return repositorio.verHistorico(idCliente, inicio, fin);
    }

    @Override
    @Transactional
    public void finalizarCarga(Cargador cargador, Carga carga, LocalDateTime recargo) {

    }

    @Override
    @Transactional
    public void altaEstacion(EstacionDeCarga estacion) {
        log.info("Registrando estación: \n departamento: " + estacion.getDepartamento() + "\n calle: " + estacion.getCalle());
        repositorio.save(estacion);
    }

    @Override
    @Transactional
    public void altaCargador(Cargador cargador) {
        log.info("Registrando cargador: \n conector de carga: " + cargador.getTipoConector() + "\n cargador de tipo: " + cargador.getTipoCargador());
        repositorio.save(cargador);
    }

    @Override
    public List<EstacionDeCarga> obtenerEstaciones() {
        List<EstacionDeCarga> estCarga = repositorio.findAll();
        log.info("Obteniendo estaciones \n Lista de estaciones: \n");
        for (EstacionDeCarga i: estCarga){
            log.info(i.getId().toString() + "\n");
        }
        return repositorio.findAll();
    }

    @Override
    public EstacionDeCarga findByIdEstacion(Long id){
        return repositorio.findByIdEstacion(id);
    }
}
