package TallerJakartaEE.ModuloDeCarga.Dominio;

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

    private String descripcion;
    private String calle;
    private String departamento;
    private double longitud;
    private double latitud;

    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cargador> cargadores;
}
