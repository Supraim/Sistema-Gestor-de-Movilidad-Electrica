package TallerJakartaEE.ModuloDeClientes.Dominio;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "clientes_cliente")
public abstract class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String cedula;
    protected String nombreCompleto;
    protected String telefono;
    protected String contra;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonbTransient
    protected List<Reclamo> reclamos = new ArrayList<>();

    public void registrarReclamo(Reclamo reclamo){
        reclamos.add(reclamo);
    }
}
