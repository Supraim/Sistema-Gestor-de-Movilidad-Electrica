package TallerJakartaEE.ModuloDeCarga.Aplicacion.Implementacion;

import TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz.ServicioCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstadoCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstadoCargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.Repositorio.CargaRepositorio;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cliente;
import TallerJakartaEE.ModuloDeCarga.Interfaces.Evento.Out.PublicadorEventoCarga;

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

    @Inject
    private PublicadorEventoCarga publicadorEvento;

    @Override
    @Transactional
    public void iniciarCarga(Long idCliente, Long idCargador, Long medioDePagoId) {
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
        carga.setFechaInicio(LocalDateTime.now());
        carga.setEstado(EstadoCarga.CARGANDO);
        carga.setPorcentajeAvance(0);
        carga.setCargador(cargador);
        carga.setMedioDePagoId(medioDePagoId);

        repositorio.save(carga);

        // Asociar al cliente
        cliente.iniciarCarga(carga);
        repositorio.updateCliente(cliente);

        // Marcar cargador en uso
        cargador.setEstado(EstadoCargador.EN_USO);

        log.info("Carga iniciada para cliente: " + cliente.getNombreCompleto() + " en cargador: " + idCargador);
    }

    @Override
    public Carga verCargaActual(Long idCliente) {
        return repositorio.verCargaActual(idCliente);
    }

    @Override
    public List<Carga> verHistorico(Long idCliente, LocalDateTime inicio, LocalDateTime fin) {
        return repositorio.verHistorico(idCliente, inicio, fin);
    }

    @Override
    @Transactional
    public void finalizarCarga(Long idCargador, float consumo, float recargo) {
        // Buscar la carga activa en este cargador
        Carga carga = repositorio.findCargaActivaPorCargador(idCargador);
        if (carga == null) {
            throw new IllegalArgumentException("No hay carga activa en el cargador: " + idCargador);
        }

        // Finalizar la carga
        carga.setFechaFin(LocalDateTime.now());
        carga.setImporteTotal(consumo);
        carga.setRecargoPorDemora(recargo);
        carga.setEstado(EstadoCarga.COMPLETA);
        carga.setPorcentajeAvance(100);

        // Eliminar la carga activa del cliente
        Cliente cliente = carga.getCliente();
        cliente.finalizarCarga();
        repositorio.updateCliente(cliente);

        // Marcar el cargador como disponible
        Cargador cargador = repositorio.findById(idCargador);
        cargador.setEstado(EstadoCargador.DISPONIBLE);

        log.info("Carga finalizada para cliente: " + cliente.getNombreCompleto()
                + " | Consumo: " + consumo + " | Recargo: " + recargo);

        // Disparar evento hacia ModuloDePagos para cobrar la carga
        publicadorEvento.publicarCargaFinalizada(cliente.getId(), consumo, recargo, carga.getMedioDePagoId());
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
