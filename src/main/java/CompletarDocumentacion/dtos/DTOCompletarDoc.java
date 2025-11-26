package CompletarDocumentacion.dtos;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class DTOCompletarDoc {
    private int nroTramite;
    private String cuitCliente;
    private String nombreApellidoCliente;
    private String nombreTipoTramite;
    private Timestamp fechaHoraFinEntregaDoc;
    private Timestamp fechaVencimientoDocumentacion;
    private List<DTODocumentacion> dtoDocumentacion = new ArrayList<>();
    

    
    // Getters y Setters
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public String getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public Timestamp getFechaHoraFinEntregaDoc() {
        return fechaHoraFinEntregaDoc;
    }

    public void setFechaHoraFinEntregaDoc(Timestamp fechaHoraFinEntregaDoc) {
        this.fechaHoraFinEntregaDoc = fechaHoraFinEntregaDoc;
    }

    public Timestamp getFechaVencimientoDocumentacion() {
        return fechaVencimientoDocumentacion;
    }

    public void setFechaVencimientoDocumentacion(Timestamp fechaVencimientoDocumentacion) {
        this.fechaVencimientoDocumentacion = fechaVencimientoDocumentacion;
    }

    public List<DTODocumentacion> getDtoDocumentacion() {
        return dtoDocumentacion;
    }

    public void setDtoDocumentacion(List<DTODocumentacion> dtoDocumentacion) {
        this.dtoDocumentacion = dtoDocumentacion;
    }

    // Método para agregar un DTODocumentacion a la lista
    public void addDtoDocumentacion(DTODocumentacion documentacion) {
        this.dtoDocumentacion.add(documentacion);
    }

}
