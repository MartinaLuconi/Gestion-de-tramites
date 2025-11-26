package entidades;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Documentacion extends Entidad {
    private int codDocumentacion;
    private String nombreDocumentacion;
    private Timestamp fechaHoraBajaDocumentacion;
    private String descripcionDocumentacion;
    
    // Constructor
    public Documentacion() {
    }
    
    // Getters y Setters
    public int getCodDocumentacion() {
        return codDocumentacion;
    }

    public void setCodDocumentacion(int codDocumentacion) {
        this.codDocumentacion = codDocumentacion;
    }

    public String getNombreDocumentacion() {
        return nombreDocumentacion;
    }

    public void setNombreDocumentacion(String nombreDocumentacion) {
        this.nombreDocumentacion = nombreDocumentacion;
    }

    public Timestamp getFechaHoraBajaDocumentacion() {
        return fechaHoraBajaDocumentacion;
    }

    public void setFechaHoraBajaDocumentacion(Timestamp fechaHoraBajaDocumentacion) {
        this.fechaHoraBajaDocumentacion = fechaHoraBajaDocumentacion;
    }

    public String getDescripcionDocumentacion() {
        return descripcionDocumentacion;
    }

    public void setDescripcionDocumentacion(String descripcionDocumentacion) {
        this.descripcionDocumentacion = descripcionDocumentacion;
    }
    
}
