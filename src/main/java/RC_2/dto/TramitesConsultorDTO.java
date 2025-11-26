package RC_2.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TramitesConsultorDTO {

    private String cuitCliente;
    private Timestamp fechaHoraCargaTramite;
    private String nombreEstadoTramite;
    private String nombreTipoTramite;
    private int nroTramite;
    private int legajoConsultor;
    private String nombreConsultor;
    private datosConsultorDTO consultorAsignado;

    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }

    public String getNombreConsultor() {
        return nombreConsultor;
    }

    public void setNombreConsultor(String nombreConsultor) {
        this.nombreConsultor = nombreConsultor;
    }
    
    //METODOS
    public String getCuitCliente() {
        return cuitCliente;
    }
    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
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
    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }
    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }
    public int getNroTramite() {
        return nroTramite;
    }
    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public datosConsultorDTO getConsultorAsignado() {
        return consultorAsignado;
    }

    public void setConsultorAsignado(datosConsultorDTO consultorAsignado) {
        this.consultorAsignado = consultorAsignado;
    }

    
    
    
    
}
