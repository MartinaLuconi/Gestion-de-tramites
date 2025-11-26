package ABMDocumentacion.dtos;

import java.sql.Timestamp;

public class DocumentacionDTO {
    private int codDocumentacion;
    private String nombreDocumentacion;
    private Timestamp fechaHoraBajaDocumentacion;

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
}
