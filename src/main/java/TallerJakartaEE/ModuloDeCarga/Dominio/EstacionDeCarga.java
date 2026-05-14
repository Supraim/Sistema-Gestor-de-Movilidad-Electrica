package TallerJakartaEE.ModuloDeCarga.Dominio;

import java.util.List;

public class EstacionDeCarga {
    private String descripcion;
    private String calle;
    private String departamento;
    private int longitud;
    private int latitud;
    private List<Cargador> cargadores;

    public EstacionDeCarga(){

    }
    public EstacionDeCarga(String descripcion, String calle, String departamento, int longitud, int latitud){
        this.descripcion = descripcion;
        this.calle = calle;
        this.departamento = departamento;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public List<Cargador> getCargadores() {
        return cargadores;
    }

    public void setCargadores(List<Cargador> cargadores) {
        this.cargadores = cargadores;
    }
}
