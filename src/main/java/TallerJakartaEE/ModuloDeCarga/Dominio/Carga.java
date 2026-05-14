package TallerJakartaEE.ModuloDeCarga.Dominio;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class Carga {
    private Date fecha;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private float importeTotal;
    private float recargoPorDemora;
    private int porcentajeAvance;
    private LocalDateTime horaEstimadaFin;
    private EstadoCarga estado;

    public Carga() {}

    public Carga(Date fecha, LocalDateTime horaInicio, LocalDateTime horaFin, float importeTotal, float recargoPorDemora, EstadoCarga estado) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.importeTotal = importeTotal;
        this.recargoPorDemora = recargoPorDemora;
        this.estado = estado;
    }

    public Carga(Date fecha, LocalDateTime horaInicio, LocalDateTime horaFin, float importeTotal, float recargoPorDemora, int porcentajeAvance, LocalDateTime horaEstimadaFin, EstadoCarga estado) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.importeTotal = importeTotal;
        this.recargoPorDemora = recargoPorDemora;
        this.porcentajeAvance = porcentajeAvance;
        this.horaEstimadaFin = horaEstimadaFin;
        this.estado = estado;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public float getRecargoPorDemora() {
        return recargoPorDemora;
    }

    public void setRecargoPorDemora(float recargoPorDemora) {
        this.recargoPorDemora = recargoPorDemora;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public LocalDateTime getHoraEstimadaFin() {
        return horaEstimadaFin;
    }

    public void setHoraEstimadaFin(LocalDateTime horaEstimadaFin) {
        this.horaEstimadaFin = horaEstimadaFin;
    }

    public EstadoCarga getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarga estado) {
        this.estado = estado;
    }
}
