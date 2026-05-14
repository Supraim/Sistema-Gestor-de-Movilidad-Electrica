package TallerJakartaEE.ModuloDeCarga.Dominio;

import java.time.LocalDateTime;

public class Cargador {
    private TipoCargador tipoCargador;
    private boolean tieneCable;
    private TipoConector tipoConector;
    private EstadoCargador estado;
    private LocalDateTime tiempoEstimadoFinalizacion;
    private LocalDateTime fechaEstimadaReparacion;
    private int potenciaMinima;

    public Cargador() {}

    public Cargador(TipoCargador tipo, boolean tieneCable, TipoConector tipoConector, EstadoCargador estado, LocalDateTime fechaEstimadaReparacion, int potenciaMinima) {
        this.tipoCargador = tipo;
        this.tieneCable = tieneCable;
        this.tipoConector = tipoConector;
        this.estado = estado;
    }

    public Cargador(TipoCargador tipo, boolean tieneCable, TipoConector tipoConector, EstadoCargador estado, LocalDateTime tiempoEstimadoFinalizacion, LocalDateTime fechaEstimadaReparacion, int potenciaMinima) {
        this.tipoCargador = tipo;
        this.tieneCable = tieneCable;
        this.tipoConector = tipoConector;
        this.estado = estado;
        this.tiempoEstimadoFinalizacion = tiempoEstimadoFinalizacion;
        this.fechaEstimadaReparacion = fechaEstimadaReparacion;
        this.potenciaMinima = potenciaMinima;
    }

    public TipoCargador getTipoCargador() {
        return tipoCargador;
    }

    public void setTipoCargador(TipoCargador tipoCargador) {
        this.tipoCargador = tipoCargador;
    }

    public boolean isTieneCable() {
        return tieneCable;
    }

    public void setTieneCable(boolean tieneCable) {
        this.tieneCable = tieneCable;
    }

    public TipoConector getTipoConector() {
        return tipoConector;
    }

    public void setTipoConector(TipoConector tipoConector) {
        this.tipoConector = tipoConector;
    }

    public EstadoCargador getEstado() {
        return estado;
    }

    public void setEstado(EstadoCargador estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaEstimadaReparacion() {
        return fechaEstimadaReparacion;
    }

    public void setFechaEstimadaReparacion(LocalDateTime fechaEstimadaReparacion) {
        this.fechaEstimadaReparacion = fechaEstimadaReparacion;
    }

    public int getPotenciaMinima() {
        return potenciaMinima;
    }

    public void setPotenciaMinima(int potenciaMinima) {
        this.potenciaMinima = potenciaMinima;
    }
    
    public LocalDateTime getTiempoEstimadoFinalizacion() {
        return tiempoEstimadoFinalizacion;
    }

    public void setTiempoEstimadoFinalizacion(LocalDateTime tiempoEstimadoFinalizacion) {
        this.tiempoEstimadoFinalizacion = tiempoEstimadoFinalizacion;
    }
}
