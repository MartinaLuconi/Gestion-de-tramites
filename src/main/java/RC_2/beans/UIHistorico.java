/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;

import java.sql.Timestamp;

/**
 *
 * @author marti
 */
public class UIHistorico {
    private int nroLineaTramiteEstado;
    private int codEstadoTramite;
    private Timestamp fechaBajaTramiteEstado;
    private Timestamp fechaCambio; //fecha Hasta de TramiteEstado
    private String observacionTramiteEstado; 
    private String nombreEstadoTramite;

    public String getObservacionTramiteEstado() {
        return observacionTramiteEstado;
    }

    public void setObservacionTramiteEstado(String observacionTramiteEstado) {
        this.observacionTramiteEstado = observacionTramiteEstado;
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

    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }

    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    public Timestamp getFechaBajaTramiteEstado() {
        return fechaBajaTramiteEstado;
    }

    public void setFechaBajaTramiteEstado(Timestamp fechaBajaTramiteEstado) {
        this.fechaBajaTramiteEstado = fechaBajaTramiteEstado;
    }

    public Timestamp getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Timestamp fechaCambio) {
        this.fechaCambio = fechaCambio;
    }
    
    
}
