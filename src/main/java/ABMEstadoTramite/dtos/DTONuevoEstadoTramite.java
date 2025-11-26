/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMEstadoTramite.dtos;

import java.sql.Timestamp;

/**
 *
 * @author vickyvelasco
 */
public class DTONuevoEstadoTramite {
    private int codEstadoTramite;
    private Timestamp fechaHoraBajaEstadoTramite;
    private String nombreEstadoTramite;

    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }

    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    public Timestamp getFechaHoraBajaEstadoTramite() {
        return fechaHoraBajaEstadoTramite;
    }

    public void setFechaHoraBajaEstadoTramite(Timestamp fechaHoraBajaEstadoTramite) {
        this.fechaHoraBajaEstadoTramite = fechaHoraBajaEstadoTramite;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }
    
    
}
