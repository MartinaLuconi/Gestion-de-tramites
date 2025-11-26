package entidades;

import java.sql.Timestamp;

public class TramiteEstado extends Entidad{
    private int nroLineaTramiteEstado;
    private String observacionTramiteEstado;
    private Timestamp fechaHoraDesdeTramiteEstado;
    private Timestamp fechaHoraHastaTramiteEstado;
    private Timestamp fechaHoraBajaTramiteEstado;
    private EstadoTramite estadoTramite;
    
    // Constructor
    public TramiteEstado() {
    }
    
    // Getters y Setters
    
    public int getNroLineaTramiteEstado() {
        return nroLineaTramiteEstado;
    }

    public void setNroLineaTramiteEstado(int nroLineaTramiteEstado) {
        this.nroLineaTramiteEstado = nroLineaTramiteEstado;
    }

    public String getObservacionTramiteEstado() {
        return observacionTramiteEstado;
    }

    public void setObservacionTramiteEstado(String observacionTramiteEstado) {
        this.observacionTramiteEstado = observacionTramiteEstado;
    }

    public Timestamp getFechaHoraDesdeTramiteEstado() {
        return fechaHoraDesdeTramiteEstado;
    }

    public void setFechaHoraDesdeTramiteEstado(Timestamp fechaHoraDesdeTramiteEstado) {
        this.fechaHoraDesdeTramiteEstado = fechaHoraDesdeTramiteEstado;
    }

    public Timestamp getFechaHoraHastaTramiteEstado() {
        return fechaHoraHastaTramiteEstado;
    }

    public void setFechaHoraHastaTramiteEstado(Timestamp fechaHoraHastaTramiteEstado) {
        this.fechaHoraHastaTramiteEstado = fechaHoraHastaTramiteEstado;
    }

    public Timestamp getFechaHoraBajaTramiteEstado() {
        return fechaHoraBajaTramiteEstado;
    }

    public void setFechaHoraBajaTramiteEstado(Timestamp fechaHoraBajaTramiteEstado) {
        this.fechaHoraBajaTramiteEstado = fechaHoraBajaTramiteEstado;
    }

    public EstadoTramite getEstadoTramite() {
        return estadoTramite;
    }

    public void setEstadoTramite(EstadoTramite estadoTramite) {
        this.estadoTramite = estadoTramite;
    }
}
