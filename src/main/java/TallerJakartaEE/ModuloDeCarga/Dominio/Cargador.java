package TallerJakartaEE.ModuloDeCarga.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "carga_cargador")
public class Cargador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoCargador tipoCargador;

    private boolean tieneCable;

    @Enumerated(EnumType.STRING)
    private TipoConector tipoConector;

    @Enumerated(EnumType.STRING)
    private EstadoCargador estado;

    private int potenciaMinima;

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private EstacionDeCarga estacion;

    public Cargador() {}

    public Cargador(TipoCargador tipoCargador, boolean tieneCable, TipoConector tipoConector, EstadoCargador estado, int potenciaMinima) {
        this.tipoCargador = tipoCargador;
        this.tieneCable = tieneCable;
        this.tipoConector = tipoConector;
        this.estado = estado;
        this.potenciaMinima = potenciaMinima;
    }

    public Long getId() { return id; }
    public TipoCargador getTipoCargador() { return tipoCargador; }
    public void setTipoCargador(TipoCargador tipoCargador) { this.tipoCargador = tipoCargador; }
    public boolean isTieneCable() { return tieneCable; }
    public void setTieneCable(boolean tieneCable) { this.tieneCable = tieneCable; }
    public TipoConector getTipoConector() { return tipoConector; }
    public void setTipoConector(TipoConector tipoConector) { this.tipoConector = tipoConector; }
    public EstadoCargador getEstado() { return estado; }
    public void setEstado(EstadoCargador estado) { this.estado = estado; }
    public int getPotenciaMinima() { return potenciaMinima; }
    public void setPotenciaMinima(int potenciaMinima) { this.potenciaMinima = potenciaMinima; }
    public EstacionDeCarga getEstacion() { return estacion; }
    public void setEstacion(EstacionDeCarga estacion) { this.estacion = estacion; }
}
