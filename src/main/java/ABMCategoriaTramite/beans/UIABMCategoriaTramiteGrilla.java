/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCategoriaTramite.beans;

import java.sql.Timestamp;

/**
 *
 * @author vickyvelasco
 */
public class UIABMCategoriaTramiteGrilla {
    private int codCategoriaTramite;
    private String nombreCategoriaTramite;
    private Timestamp fechaHoraBajaCategoriaTramite;

    public int getCodCategoriaTramite() {
        return codCategoriaTramite;
    }

    public void setCodCategoriaTramite(int codCategoriaTramite) {
        this.codCategoriaTramite = codCategoriaTramite;
    }

    public String getNombreCategoriaTramite() {
        return nombreCategoriaTramite;
    }

    public void setNombreCategoriaTramite(String nombreCategoriaTramite) {
        this.nombreCategoriaTramite = nombreCategoriaTramite;
    }

    public Timestamp getFechaHoraBajaCategoriaTramite() {
        return fechaHoraBajaCategoriaTramite;
    }

    public void setFechaHoraBajaCategoriaTramite(Timestamp fechaHoraBajaCategoriaTramite) {
        this.fechaHoraBajaCategoriaTramite = fechaHoraBajaCategoriaTramite;
    }
    
    
    
}
