package TallerJakartaEE.ModuloDeClientes.Aplicacion;

import TallerJakartaEE.ModuloDeCarga.Interfaces.CargaActualDTO;
import TallerJakartaEE.ModuloDeCarga.Interfaces.CargaQueryPort;
import TallerJakartaEE.ModuloDeCarga.Interfaces.CargaResumenDTO;

import java.time.LocalDate;
import java.util.List;

public class ClienteCargaConsultaServicio {
    private final CargaQueryPort cargaQueryPort;

    public ClienteCargaConsultaServicio(CargaQueryPort cargaQueryPort) {
        this.cargaQueryPort = cargaQueryPort;
    }

    public CargaActualDTO verCargaActualCliente(String clienteId) {
        return cargaQueryPort.verCargaActual(clienteId);
    }

    public List<CargaResumenDTO> verHistoricoCliente(String clienteId, LocalDate desde, LocalDate hasta) {
        return cargaQueryPort.verHistorico(clienteId, desde, hasta);
    }
}
