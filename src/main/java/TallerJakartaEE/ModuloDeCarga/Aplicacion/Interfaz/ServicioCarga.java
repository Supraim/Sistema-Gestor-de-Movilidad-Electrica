package TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz;

import TallerJakartaEE.ModuloDeCarga.Dominio.Carga;
import TallerJakartaEE.ModuloDeCarga.Dominio.Cargador;
import TallerJakartaEE.ModuloDeCarga.Dominio.EstacionDeCarga;
import TallerJakartaEE.ModuloDeClientes.Dominio.Cliente;
import TallerJakartaEE.ModuloDePagos.Dominio.MedioDePago;

import java.time.LocalDateTime;
import java.util.List;

public interface ServicioCarga {
    public void iniciarCarga(Cliente cliente, MedioDePago medioDePago);
    public Carga verCargaActual(Cliente cliente);
    public List<Carga> verHistorico(Cliente cliente, LocalDateTime inicio, LocalDateTime fin);
    public void finalizarCarga(Cargador cargador, Carga carga, LocalDateTime recargo);
    public void altaEstacion(EstacionDeCarga estacion);
    public void altaCargador(Cargador cargador);
    public List<EstacionDeCarga> obtenerEstaciones();
}
