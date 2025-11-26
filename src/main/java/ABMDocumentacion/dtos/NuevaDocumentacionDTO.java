package ABMDocumentacion.dtos;

public class NuevaDocumentacionDTO {
    private int codDocumentacion;
    private String nombreDocumentacion;

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
}
