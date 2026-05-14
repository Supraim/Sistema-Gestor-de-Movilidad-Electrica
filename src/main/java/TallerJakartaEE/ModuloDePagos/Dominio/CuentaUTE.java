package TallerJakartaEE.ModuloDePagos.Dominio;

public class CuentaUTE extends MedioDePago {
    private String numeroCuenta;

    public CuentaUTE() {

    }

    public CuentaUTE(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}
