package TallerJakartaEE.ModuloDeClientes.Infraestructura.Messaging;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

import java.util.logging.Logger;

@ApplicationScoped
public class ReclamoProducer {
    private static final Logger log = Logger.getLogger(ReclamoProducer.class.getName());

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory fabricaConexion;

    @Resource(lookup = "java:/jms/queue/colaReclamos")
    private Queue colaReclamos;

    public void enviarReclamo(Long clienteId, String comentario) {
        try (JMSContext context = fabricaConexion.createContext()) {
            String mensaje = clienteId + "|" + comentario;
            context.createProducer().send(colaReclamos, mensaje);
            log.info("Reclamo enviado a la cola: " + mensaje);
        }
    }
}
