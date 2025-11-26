package entidades;

import java.sql.Timestamp;


public class CategoriaTramite extends Entidad{
    public int codCategoriaTramite;
    public String nombreCategoriaTramite;
    public Timestamp fechaHoraBajaCategoriaTramite;
    
    // Constructor
    public CategoriaTramite() {
    }
    
    // Getters y Setters
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
