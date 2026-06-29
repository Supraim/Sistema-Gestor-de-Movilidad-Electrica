package TallerJakartaEE.ModuloDeMonitoreo.Infraestructura;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxApiVersion;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.util.logging.Logger;

@ApplicationScoped
public class RegistradorDeMetricas {
    private static final Logger log = Logger.getLogger(RegistradorDeMetricas.class.getName());

    public static final String CARGAS_ACTIVAS = "cargasActivas";
    public static final String CARGAS_REALIZADAS = "cargasRealizadas";
    public static final String PAGOS_UTE = "pagosUTE";
    public static final String PAGOS_TARJETA = "pagosTarjeta";
    public static final String ERROR_PAGO_TARJETA = "errorPagoTarjeta";

    private MeterRegistry meterRegistry;

    @PostConstruct
    public void init() {
        InfluxConfig config = new InfluxConfig() {
            @Override
            public String get(String s) {
                return null;
            }

            // configurable
            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }

            @Override
            public String uri() {
                return "http://localhost:8086";
            }

            @Override
            public String org() {
                return "tallerjava";
            }

            @Override
            public String bucket() {
                return "metricasTallerJava";
            }

            @Override
            public String token() {
                return "mi-token-tallerjava";
            }

            @Override
            public InfluxApiVersion apiVersion() {
                return InfluxApiVersion.V2;
            }
        };

        meterRegistry = new InfluxMeterRegistry(config, Clock.SYSTEM);
    }

    public void incrementarCounter(String nombreCounter) {
        meterRegistry.counter(nombreCounter).increment();
        log.info("Metrica incrementada: " + nombreCounter);
    }
}
