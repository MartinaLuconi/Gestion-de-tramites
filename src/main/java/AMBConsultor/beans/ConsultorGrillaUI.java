package AMBConsultor.beans;

import java.sql.Timestamp;

/**
 *
 * @author adrie
 */
public class ConsultorGrillaUI  {

    private int legajoConsultor;
    private String nombreApellidoConsultor;
    private int consultorNroMaxTramite;
    private Timestamp fechaHoraBajaConsultor;
  
    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }

    public String getNombreApellidoConsultor() {
        return nombreApellidoConsultor;
    }

    public void setNombreApellidoConsultor(String nombreApellidoConsultor) {
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }
    
    public int getConsultorNroMaxTramite() {
        return consultorNroMaxTramite;
    }

    public void setConsultorNroMaxTramite(int consultorNroMaxTramite) {
        this.consultorNroMaxTramite = consultorNroMaxTramite;
    }
    public Timestamp getFechaHoraBajaConsultor() {
        return fechaHoraBajaConsultor;
    }

    public void setFechaHoraBajaConsultor(Timestamp fechaHoraBajaConsultor) {
        this.fechaHoraBajaConsultor = fechaHoraBajaConsultor;
    }
}
