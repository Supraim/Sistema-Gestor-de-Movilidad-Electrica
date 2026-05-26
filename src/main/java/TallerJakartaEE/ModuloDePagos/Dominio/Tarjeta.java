package TallerJakartaEE.ModuloDePagos.Dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes_reclamo")
public class Tarjeta extends MedioDePago {
    private String numero;
    private Date fechaVencimiento;
    private String digitoVerificador;
    private TipoTarjeta tipo;
}
