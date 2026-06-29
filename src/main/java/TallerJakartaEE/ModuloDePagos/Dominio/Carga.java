package TallerJakartaEE.ModuloDePagos.Dominio;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "pagos_carga")
@Table(name = "pagos_carga")
public class Carga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private float importeTotal;
}
