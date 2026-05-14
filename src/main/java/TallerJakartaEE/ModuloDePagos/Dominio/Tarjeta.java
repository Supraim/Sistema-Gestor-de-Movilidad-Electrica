package TallerJakartaEE.ModuloDePagos.Dominio;

import java.util.Date;

public class Tarjeta extends MedioDePago {
    private String numero;
    private Date fechaVencimiento;
    private String digitoVerificador;
    private TipoTarjeta tipo;

    public Tarjeta() {}

    public Tarjeta(String numero, Date fechaVencimiento, String digitoVerificador, TipoTarjeta tipo) {
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.digitoVerificador = digitoVerificador;
        this.tipo = tipo;
    }

    public TipoTarjeta getTipo() {
        return tipo;
    }

    public void setTipo(TipoTarjeta tipo) {
        this.tipo = tipo;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
