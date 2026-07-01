package TallerJakartaEE.ModuloDeClientes.Dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes_reclamo")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String comentario;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String etiqueta; // POSITIVO, NEGATIVO, NEUTRO (el LLM lo asigna)

    public Reclamo(String comentario, LocalDateTime fecha, Cliente cliente){
        this.comentario = comentario;
        this.fecha = fecha;
        this.cliente = cliente;
    }
}
