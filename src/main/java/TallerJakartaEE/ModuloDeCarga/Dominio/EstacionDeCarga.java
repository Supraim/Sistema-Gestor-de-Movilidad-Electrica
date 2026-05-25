package TallerJakartaEE.ModuloDeCarga.Dominio;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "carga_estacion")
public class EstacionDeCarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String calle;
    private String departamento;
    private double longitud;
    private double latitud;

    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cargador> cargadores;

    public EstacionDeCarga() {}

    public EstacionDeCarga(String descripcion, String calle, String departamento, double longitud, double latitud) {
        this.descripcion = descripcion;
        this.calle = calle;
        this.departamento = departamento;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Long getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public List<Cargador> getCargadores() { return cargadores; }
    public void setCargadores(List<Cargador> cargadores) { this.cargadores = cargadores; }
}
