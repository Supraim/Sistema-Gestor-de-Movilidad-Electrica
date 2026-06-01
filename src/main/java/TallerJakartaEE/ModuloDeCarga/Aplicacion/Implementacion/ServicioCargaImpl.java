package TallerJakartaEE.ModuloDeCarga.Aplicacion.Implementacion;

import TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz.ServicioCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Repositorio.CargaRepositorio;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;
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
    public void iniciarCarga(Cliente cliente, MedioDePago medioDePago) {

    }

    @Override
    public Carga verCargaActual(Cliente cliente) {
        return null;
    }

    @Override
    public List<Carga> verHistorico(Cliente cliente, LocalDateTime inicio, LocalDateTime fin) {
        return null;
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
        return null;
    }
}
