package TallerJakartaEE.ModuloDeClientes.Interfaces.Evento.Out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedioPagoRegistradoEvento {
    private Long clienteId;
    private String tipo;                // "TARJETA" o "CUENTA_UTE"
    // Datos de tarjeta
    private String numeroTarjeta;
    private String fechaVencimiento;
    private String digitoVerificador;
    private String tipoTarjeta;         // "VISA", "MASTER", "AMEX"
    // Datos de cuenta UTE
    private String numeroCuenta;
}
