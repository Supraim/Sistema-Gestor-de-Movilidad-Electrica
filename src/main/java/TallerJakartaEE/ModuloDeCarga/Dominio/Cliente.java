package TallerJakartaEE.ModuloDeCarga.Dominio;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "carga_cliente")
@Table(name = "carga_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;
    private String nombreCompleto;

    @OneToOne
    @JoinColumn(name = "carga_activa_id")
    @JsonbTransient
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Carga cargaActiva;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonbTransient
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Carga> historicoCargas = new ArrayList<>();

    public Cliente(String cedula, String nombreCompleto) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
    }

    public void iniciarCarga(Carga carga) {
        this.cargaActiva = carga;
        this.historicoCargas.add(carga);
    }

    public void finalizarCarga() {
        this.cargaActiva = null;
    }
}
