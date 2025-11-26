package CargarTramite.beans;

import java.sql.Timestamp;
import java.util.Date;

public class TramiteGrillaUI {

    private int nroTramite;
    private String CUITCliente;
    private String nombreCliente;
    private String nombreTipoTramite;
    private String nombreConsultor;
    private String nombreEstadoTramite;
    private Date fechaCargaTramite;
    private String estadoDocumentacion;
    private Date fechaHoraBajaTramite;
    private Date fechaHoraFinTramite;

    // Getters y Setters
    
    
    public Date getFechaHoraFinTramite() {
        return fechaHoraFinTramite;
    }

    public void setFechaHoraFinTramite(Date fechaHoraFinTramite) {
        this.fechaHoraFinTramite = fechaHoraFinTramite;
    }
    
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public String getCUITCliente() {
        return CUITCliente;
    }

    public void setCUITCliente(String cUITCliente) {
        this.CUITCliente = cUITCliente;
    }

    public Date getFechaHoraBajaTramite() {
        return fechaHoraBajaTramite;
    }

    public void setFechaHoraBajaTramite(Date fechaHoraBajaTramite) {
        this.fechaHoraBajaTramite = fechaHoraBajaTramite;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public String getNombreConsultor() {
        return nombreConsultor;
    }

    public void setNombreConsultor(String nombreConsultor) {
        this.nombreConsultor = nombreConsultor;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public Date getFechaCargaTramite() {
        return fechaCargaTramite;
    }

    public void setFechaCargaTramite(Date fechaCargaTramite) {
        this.fechaCargaTramite = fechaCargaTramite;
    }

    public String getEstadoDocumentacion() {
        return estadoDocumentacion;
    }

    public void setEstadoDocumentacion(String estadoDocumentacion) {
        this.estadoDocumentacion = estadoDocumentacion;
    }
}
