package TallerJakartaEE.ModuloDeCarga.Dominio;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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
    @JsonbTransient
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Cliente cliente;
    
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private float importeTotal;
    private float recargoPorDemora;
    private int porcentajeAvance;
    private LocalDateTime horaEstimadaFin;

    @Enumerated(EnumType.STRING)
    private EstadoCarga estado;

    @ManyToOne
    @JoinColumn(name = "cargador_id")
    private Cargador cargador;
}
