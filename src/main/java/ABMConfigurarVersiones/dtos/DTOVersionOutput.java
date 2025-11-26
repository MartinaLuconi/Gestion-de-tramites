/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.dtos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julie
 */
public class DTOVersionOutput {
    private int codVersion;
    private Timestamp fechaHoraDesdeVersion;
    private Timestamp fechaHoraHastaVersion;
    private int codTipoTramite;
    private List<DTOEstadoActualOutput> estadosActuales = new ArrayList();   
    private String draw;

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }
    
    public int getCodVersion() {
        return codVersion;
    }

    public void setCodVersion(int codVersion) {
        this.codVersion = codVersion;
    }

    public Timestamp getFechaHoraDesdeVersion() {
        return fechaHoraDesdeVersion;
    }

    public void setFechaHoraDesdeVersion(Timestamp fechaHoraDesdeVersion) {
        this.fechaHoraDesdeVersion = fechaHoraDesdeVersion;
    }

    public Timestamp getFechaHoraHastaVersion() {
        return fechaHoraHastaVersion;
    }

    public void setFechaHoraHastaVersion(Timestamp fechaHoraHastaVersion) {
        this.fechaHoraHastaVersion = fechaHoraHastaVersion;
    }

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }
    
    public void addEstadosActuales (DTOEstadoActualOutput estadoActual){
        estadosActuales.add(estadoActual);
    }

    public List<DTOEstadoActualOutput> getEstadosActuales() {
        return estadosActuales;
    }

    public void setEstadosActuales(List<DTOEstadoActualOutput> estadosActuales) {
        this.estadosActuales = estadosActuales;
    }
}
