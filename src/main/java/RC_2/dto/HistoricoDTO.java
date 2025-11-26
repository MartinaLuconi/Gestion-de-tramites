package RC_2.dto;

import java.sql.Timestamp;
import java.util.Date;

public class HistoricoDTO {
    private int codEstadoTramite;
    private Timestamp fechaHastaTramiteEstado;
    private Timestamp fechaHoraBajaTramiteEstado;
    private String nombreEstadoTramite;
    private int nroLineaTramiteEstado;
    private String observaciones;

    //METODOS 

    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }

    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    public Timestamp getFechaHastaTramiteEstado() {
        return fechaHastaTramiteEstado;
    }

    public void setFechaHastaTramiteEstado(Timestamp fechaHastaTramiteEstado) {
        this.fechaHastaTramiteEstado = fechaHastaTramiteEstado;
    }

    public Timestamp getFechaHoraBajaTramiteEstado() {
        return fechaHoraBajaTramiteEstado;
    }

    public void setFechaHoraBajaTramiteEstado(Timestamp fechaHoraBajaTramiteEstado) {
        this.fechaHoraBajaTramiteEstado = fechaHoraBajaTramiteEstado;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public int getNroLineaTramiteEstado() {
        return nroLineaTramiteEstado;
    }

    public void setNroLineaTramiteEstado(int nroLineaTramiteEstado) {
        this.nroLineaTramiteEstado = nroLineaTramiteEstado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
}
