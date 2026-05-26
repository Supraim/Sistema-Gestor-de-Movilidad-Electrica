package TallerJakartaEE.ModuloDePagos.Dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagos_cuenta_ute")
public class CuentaUTE extends MedioDePago {
    private String numeroCuenta;
}
