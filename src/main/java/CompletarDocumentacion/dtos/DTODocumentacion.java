package CompletarDocumentacion.dtos;

import java.sql.Timestamp;

public class DTODocumentacion {
    private int codTramiteDocumentacion;
    private String nombreDocumentacion;
    private Timestamp fechaHoraEntrega;
    
    
    // Getters y Setters
    public int getCodTramiteDocumentacion() {
        return codTramiteDocumentacion;
    }

    public void setCodTramiteDocumentacion(int codTramiteDocumentacion) {
        this.codTramiteDocumentacion = codTramiteDocumentacion;
    }

    public String getNombreDocumentacion() {
        return nombreDocumentacion;
    }

    public void setNombreDocumentacion(String nombreDocumentacion) {
        this.nombreDocumentacion = nombreDocumentacion;
    }

    public Timestamp getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Timestamp fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }
    
}
