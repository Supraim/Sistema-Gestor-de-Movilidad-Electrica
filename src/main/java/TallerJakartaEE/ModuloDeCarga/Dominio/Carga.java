package TallerJakartaEE.ModuloDeCarga.Dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carga_carga")
public class Carga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Date fecha;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private float importeTotal;
    private float recargoPorDemora;
    private int porcentajeAvance;
    private LocalDateTime horaEstimadaFin;
    private EstadoCarga estado;

    /*
    public Carga(Date fecha, LocalDateTime horaInicio, LocalDateTime horaFin, float importeTotal, float recargoPorDemora, EstadoCarga estado) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.importeTotal = importeTotal;
        this.recargoPorDemora = recargoPorDemora;
        this.estado = estado;
    }

    public Carga(Date fecha, LocalDateTime horaInicio, LocalDateTime horaFin, float importeTotal, float recargoPorDemora, int porcentajeAvance, LocalDateTime horaEstimadaFin, EstadoCarga estado) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.importeTotal = importeTotal;
        this.recargoPorDemora = recargoPorDemora;
        this.porcentajeAvance = porcentajeAvance;
        this.horaEstimadaFin = horaEstimadaFin;
        this.estado = estado;
    }
*/
}
