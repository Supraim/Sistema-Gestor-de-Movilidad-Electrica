package TallerJakartaEE.ModuloDeClientes.Dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "clientes_medio_pago")
@Table(name = "clientes_medio_pago")
public class MedioDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoMedioDePago tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    public MedioDePago(TipoMedioDePago tipo, Cliente cliente) {
        this.tipo = tipo;
        this.cliente = cliente;
    }
}