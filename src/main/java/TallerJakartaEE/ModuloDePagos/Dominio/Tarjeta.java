package TallerJakartaEE.ModuloDePagos.Dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagos_tarjeta")
public class Tarjeta extends MedioDePago {
    private String numero;
    private Date fechaVencimiento;
    private String digitoVerificador;

    @Enumerated(EnumType.STRING)
    private TipoTarjeta tipo;
}
