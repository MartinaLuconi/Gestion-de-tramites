
package CargarTramite.beans;

import CargarTramite.dtos.DTODocumentacion;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class VisualizarTramiteGrilla {

    
    private int nroTramite;
    private Timestamp fechaHoraCargaTramite;
    private String nombreEstadoTramite;
    private String nombreCliente;
    private String cuitCliente;
    private String direccion;
    private String nombreApellidoConsultor;
    private float precioTramite;
    private String nombreTipoTramite;   

    
    private Timestamp fechaHoraFinEntregaDoc;

    //para anular el boton de completar documentacion
    private Timestamp fechaHoraFinTramite;
    private Timestamp fechaHoraBajaTramite;

    
    private List<DTODocumentacion> documentacionesDTO = new ArrayList<>();
    
    
    
    //getters and setters
    
    
    public Timestamp getFechaHoraFinTramite() {
        return fechaHoraFinTramite;
    }

    public void setFechaHoraFinTramite(Timestamp fechaHoraFinTramite) {
        this.fechaHoraFinTramite = fechaHoraFinTramite;
    }

    public Timestamp getFechaHoraBajaTramite() {
        return fechaHoraBajaTramite;
    }

    public void setFechaHoraBajaTramite(Timestamp fechaHoraBajaTramite) {
        this.fechaHoraBajaTramite = fechaHoraBajaTramite;
    }
    
    public Timestamp getFechaHoraFinEntregaDoc() {
        return fechaHoraFinEntregaDoc;
    }

    public void setFechaHoraFinEntregaDoc(Timestamp fechaHoraFinEntregaDoc) {
        this.fechaHoraFinEntregaDoc = fechaHoraFinEntregaDoc;
    }
    
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public Timestamp getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }

    public void setFechaHoraCargaTramite(Timestamp fechaHoraCargaTramite) {
        this.fechaHoraCargaTramite = fechaHoraCargaTramite;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreApellidoConsultor() {
        return nombreApellidoConsultor;
    }

    public void setNombreApellidoConsultor(String nombreApellidoConsultor) {
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }

    public float getPrecioTramite() {
        return precioTramite;
    }

    public void setPrecioTramite(float precioTramite) {
        this.precioTramite = precioTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }
    

    public List<DTODocumentacion> getDocumentacionesDTO() {
        return documentacionesDTO;
    }

    public void setDocumentacionesDTO(List<DTODocumentacion> documentacionesDTO) {
        this.documentacionesDTO = documentacionesDTO;
    }

    public void addDocumentacion(DTODocumentacion dto){
        this.documentacionesDTO.add(dto);
    }
  
}
