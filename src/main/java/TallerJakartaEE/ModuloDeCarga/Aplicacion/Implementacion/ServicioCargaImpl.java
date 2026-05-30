package TallerJakartaEE.ModuloDeCarga.Aplicacion.Implementacion;

import TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz.ServicioCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public class ServicioCargaImpl implements ServicioCarga {

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

    }

    @Override
    @Transactional
    public void altaCargador(Cargador cargador) {

    }

    @Override
    public List<EstacionDeCarga> obtenerEstaciones() {
        return null;
    }
}
