package TallerJakartaEE.ModuloDeCarga.Dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carga_cargador")
public class Cargador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoCargador tipoCargador;

    private boolean tieneCable;

    @Enumerated(EnumType.STRING)
    private TipoConector tipoConector;

    @Enumerated(EnumType.STRING)
    private EstadoCargador estado;

    private int potenciaMinima;

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private EstacionDeCarga estacion;
}
