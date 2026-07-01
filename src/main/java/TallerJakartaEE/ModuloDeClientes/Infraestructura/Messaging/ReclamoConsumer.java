package TallerJakartaEE.ModuloDeClientes.Infraestructura.Messaging;

import TallerJakartaEE.ModuloDeMonitoreo.Infraestructura.RegistradorDeMetricas;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/colaReclamos"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class ReclamoConsumer implements MessageListener {
    private static final Logger log = Logger.getLogger(ReclamoConsumer.class.getName());
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";

    @PersistenceContext
    private EntityManager em;

    @Inject
    private RegistradorDeMetricas registrador;

    @Override
    public void onMessage(Message message) {
        try {
            String texto = ((TextMessage) message).getText();
            log.info("Mensaje recibido de la cola: " + texto);

            // Parsear: "clienteId|comentario"
            String[] partes = texto.split("\\|", 2);
            Long clienteId = Long.parseLong(partes[0]);
            String comentario = partes[1];

            // Catalogar con LLM
            String etiqueta = catalogarConLLM(comentario);
            log.info("Reclamo catalogado como: " + etiqueta);

            // Actualizar el reclamo en la BD con la etiqueta
            actualizarEtiquetaReclamo(clienteId, comentario, etiqueta);

            // Si es negativo, registrar en monitoreo
            if ("NEGATIVO".equalsIgnoreCase(etiqueta)) {
                registrador.incrementarCounter("reclamosNegativos");
            }
            registrador.incrementarCounter("reclamosTotales");

        } catch (JMSException e) {
            log.warning("Error al procesar mensaje de la cola: " + e.getMessage());
        }
    }

    private String catalogarConLLM(String comentario) {
        String json = String.format(
                "{\"model\":\"llama3.2:1b\",\"prompt\":\"Cataloga el siguiente texto como POSITIVO, NEGATIVO o NEUTRO. Responde UNICAMENTE con una de esas tres palabras: %s\",\"stream\":false}",
                comentario.replace("\"", "\\\"")
        );

        try (Client client = ClientBuilder.newClient()) {
            String respuesta;
            try (Response response = client.target(OLLAMA_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(json))) {

                respuesta = response.readEntity(String.class);
            }
            log.info("Respuesta LLM: " + respuesta);

            // Extraer la etiqueta de la respuesta
            if (respuesta.toUpperCase().contains("NEGATIVO")) {
                return "NEGATIVO";
            } else if (respuesta.toUpperCase().contains("POSITIVO")) {
                return "POSITIVO";
            } else {
                return "NEUTRO";
            }
        } catch (Exception e) {
            log.warning("Error al comunicarse con Ollama: " + e.getMessage());
            return "NEUTRO"; // Por defecto si el LLM no responde
        }
    }

    private void actualizarEtiquetaReclamo(Long clienteId, String comentario, String etiqueta) {
        try {
            em.createQuery("UPDATE Reclamo r SET r.etiqueta = :etiqueta WHERE r.cliente.id = :clienteId AND r.comentario = :comentario")
                    .setParameter("etiqueta", etiqueta)
                    .setParameter("clienteId", clienteId)
                    .setParameter("comentario", comentario)
                    .executeUpdate();
        } catch (Exception e) {
            log.warning("Error al actualizar etiqueta del reclamo: " + e.getMessage());
        }
    }
}
