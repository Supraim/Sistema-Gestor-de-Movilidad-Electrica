package TallerJakartaEE.ModuloDeCarga.Interfaces.Evento.In;

import TallerJakartaEE.ModuloDeCarga.Dominio.Cliente;
import TallerJakartaEE.ModuloDeCarga.Dominio.Repositorio.CargaRepositorio;
import TallerJakartaEE.ModuloDeClientes.Interfaces.Evento.Out.ClienteRegistradoEvento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.logging.Logger;

@ApplicationScoped
public class ObserverModuloCarga {
    private static final Logger log = Logger.getLogger(ObserverModuloCarga.class.getName());

    @Inject
    private CargaRepositorio repositorio;

    @Transactional
    public void onClienteRegistrado(@Observes ClienteRegistradoEvento evento) {
        log.info("Evento recibido: ClienteRegistrado - " + evento.getNombreCompleto());

        Cliente cliente = new Cliente(evento.getCedula(), evento.getNombreCompleto());
        repositorio.saveCliente(cliente);

        log.info("Cliente registrado en módulo de Carga: " + evento.getNombreCompleto());
    }
}
