package TallerJakartaEE.ModuloDeCarga.Dominio;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carga_estacion")
public class EstacionDeCarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String descripcion;

    @Lob
    private String calle;

    private String departamento;
    private double longitud;
    private double latitud;

    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonbTransient // Solución temporal en lo que no sabemos como hacer una query de listas con listas dentro
    private List<Cargador> cargadores;
}
