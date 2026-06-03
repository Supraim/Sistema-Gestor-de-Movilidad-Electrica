package TallerJakartaEE.ModuloDeCarga.Interfaces.Remota;

import TallerJakartaEE.ModuloDeCarga.Aplicacion.Interfaz.ServicioCarga;
import TallerJakartaEE.ModuloDeCarga.Dominio.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Path("/carga")
public class CargaAPI {

    @Inject
    private ServicioCarga servicioCarga;

    // curl -X POST http://localhost:8080/movilidad-electrica/api/carga/registrarEstacion \
    //   -H "Content-Type: application/json" \
    //   -d '{"descripcion":"Veni a cargar tu auto electrico con nosotros!","calle":"Roman Guerra","departamento":"Maldonado","longitud":33,"latitud":-8}'
    @POST
    @Path("/registrarEstacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarEstacion(EstacionRegistroDTO dtoEstacion){
        try{
            EstacionDeCarga estacion;
            estacion = new EstacionDeCarga(null, dtoEstacion.descripcion, dtoEstacion.calle, dtoEstacion.departamento, dtoEstacion.longitud, dtoEstacion.latitud, null);
            servicioCarga.altaEstacion(estacion);
            return Response.ok("Estacion registrada correctamente").build();
        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // curl -X POST http://localhost:8080/movilidad-electrica/api/carga/registrarCargador \
    //   -H "Content-Type: application/json" \
    //   -d '{"tipoCargador":"TIPO1","tieneCable":true,"tipoConector":"RAPIDA","estadoCargador":"DISPONIBLE","potenciaMinima":96,"estacionCargador":1}'
    @POST
    @Path("/registrarCargador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarCargador(CargadorRegistroDTO dtoCargador){
        try{

            TipoCargador tipoCar = null;
            TipoConector tipoCon = null;
            EstacionDeCarga estacionCarga = servicioCarga.findByIdEstacion(dtoCargador.estacionCargador);
            if("TIPO1".equals(dtoCargador.tipoCargador.toString())){
                tipoCar = TipoCargador.TIPO1;
            }else if("TIPO2".equals(dtoCargador.tipoCargador.toString())){
                tipoCar = TipoCargador.TIPO2;
            }else{
                return Response.ok("El tipo de cargador ingresado no existe").build();
            }
            if("NORMAL".equals(dtoCargador.tipoConector.toString())){
                tipoCon = TipoConector.NORMAL;
            }else if("RAPIDA".equals(dtoCargador.tipoConector.toString())){
                tipoCon = TipoConector.RAPIDA;
            }else{
                return Response.ok("El tipo de conector ingresado no existe").build();
            }

            Cargador cargador;
            cargador = new Cargador(null, tipoCar, dtoCargador.tieneCable, tipoCon, EstadoCargador.DISPONIBLE, dtoCargador.potenciaMinima, estacionCarga);
            if (servicioCarga.findByIdEstacion(dtoCargador.estacionCargador) == null){
                return Response.ok("No se ha podido registrar el Cargador: La estación que ingresó no existe").build();
            }else{
                servicioCarga.altaCargador(cargador);
                return Response.ok("Cargador registrado correctamente").build();
            }
        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // curl http://localhost:8080/movilidad-electrica/api/carga/todos
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEstaciones(){
        List<EstacionDeCarga> estaciones = servicioCarga.obtenerEstaciones();
        return Response.ok(estaciones).build();
    }

    // curl http://localhost:8080/movilidad-electrica/api/carga/verHistoricoDeCargas?idCliente=1&fechaInicio="2019-07-15"&fechaFin="2022-09-21"
    //FALTA POR TERMINAR (ALGO NO FUNCA) ********************************************************************
    @GET
    @Path("/verHistoricoDeCargas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verHistoricoDeCargas(
            @QueryParam("idCliente") Long idCliente,
            @QueryParam("fechaInicio") LocalDateTime fechaInicio,
            @QueryParam("fechaFin") LocalDateTime fechaFin){

        List<Carga> cargasCliente = servicioCarga.verHistorico(idCliente, fechaInicio, fechaFin);
        return Response.ok(cargasCliente).build();
    }

    // DTO
    public static class EstacionRegistroDTO {
        public String descripcion;
        public String calle;
        public String departamento;
        public int longitud;
        public int latitud;
    }

    public static class CargadorRegistroDTO {
        public TipoCargador tipoCargador; // "TIPO1", "TIPO2"
        public boolean tieneCable;
        public TipoConector tipoConector; // "NORMAL", "RAPIDA"
        public EstadoCargador estadoCargador; // "EN_USO", "DISPONIBLE", "MANTENIMIENTO", "FUERA_DE_SERVICIO"
        public int potenciaMinima;
        public Long estacionCargador;
    }
/*
    public static class ListaDeCargas {
        public String idCliente;
        public LocalDateTime fechaInicio;
        public LocalDateTime fechaFin;
    }
 */
}
