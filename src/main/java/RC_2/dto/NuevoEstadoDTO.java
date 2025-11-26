/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti
 */
public class NuevoEstadoDTO {
    private int nroTramite;
    private String nombreApellidoCliente;
    private String nombreTipoTramite;
    private String observacionTramiteEstado;
     
    
    private List<DestinosPosiblesDTO> destinosPosiblesList = new ArrayList<>(); 
    
    
     
    private String nombreEstadoTramiteActual;

    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramiteFiltro) {
        this.nroTramite = nroTramiteFiltro;
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

    public String getObservacionTramiteEstado() {
        return observacionTramiteEstado;
    }

    public void setObservacionTramiteEstado(String observacionTramiteEstado) {
        this.observacionTramiteEstado = observacionTramiteEstado;
    }

    public List<DestinosPosiblesDTO> getDestinosPosiblesList() {
        return destinosPosiblesList;
    }

    public void setDestinosPosiblesList(List<DestinosPosiblesDTO> destinosPosiblesList) {
        this.destinosPosiblesList = destinosPosiblesList;
    }

    public void addDestinosPosiblesList(DestinosPosiblesDTO destinosPosibles) {
        this.destinosPosiblesList.add(destinosPosibles);
    }
   

    public String getNombreEstadoTramiteActual() {
        return nombreEstadoTramiteActual;
    }

    public void setNombreEstadoTramiteActual(String nombreEstadoTramiteActual) {
        this.nombreEstadoTramiteActual = nombreEstadoTramiteActual;
    }
    
    
     
     
    
}
