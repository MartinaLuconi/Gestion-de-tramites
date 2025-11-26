package entidades;

import java.sql.Timestamp;

public class EstadoTramite extends Entidad{
private int codEstadoTramite;
    private String nombreEstadoTramite;
    private Timestamp fechaHoraBajaEstadoTramite;
    
    // Constructor
    public EstadoTramite(){
    }
    
    // Getters y Setters
    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }

    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public Timestamp getFechaHoraBajaEstadoTramite() {
        return fechaHoraBajaEstadoTramite;
    }

    public void setFechaHoraBajaEstadoTramite(Timestamp fechaHoraBajaEstadoTramite) {
        this.fechaHoraBajaEstadoTramite = fechaHoraBajaEstadoTramite;
    }
    
}
