package entidades;

import java.sql.Timestamp;

public class TramiteDocumentacion extends Entidad { 
    private int codTramiteDocumentacion;
    private Timestamp fechaHoraEntrega;
    private Documentacion documentacion;
    private byte[] archivo;
    private String nombreArchivo;
 
    // Constructor
    public TramiteDocumentacion(){}
         
    // Getters and Setters
    public int getCodTramiteDocumentacion() {
        return codTramiteDocumentacion;
    }

    public void setCodTramiteDocumentacion(int codTramiteDocumentacion) {
        this.codTramiteDocumentacion = codTramiteDocumentacion;
    }
    
    public Timestamp getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Timestamp fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public Documentacion getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(Documentacion documentacion) {
        this.documentacion = documentacion;
    }
    
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
}