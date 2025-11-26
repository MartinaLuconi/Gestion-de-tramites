
package ABMTipoTramite.dtos;

import java.sql.Timestamp;

public class DTOTipoTramite {
    
    private int codTipoTramite;
    private Timestamp fechaHoraBajaTipoTramite;
    private int maxDiaEntrega;
    private String nombreCategoriaTramite;
    private String nombreTipoTramite;
    private String nombreDocumentacion;
    private String documentacion;
    private String categoriaTramite;

    public String getCategoriaTramite() {
        return categoriaTramite;
    }

    public void setCategoriaTramite(String categoriaTramite) {
        this.categoriaTramite = categoriaTramite;
    }
    

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }
    

    public String getNombreDocumentacion() {
        return nombreDocumentacion;
    }

    public void setNombreDocumentacion(String nombreDocumentacion) {
        this.nombreDocumentacion = nombreDocumentacion;
    }

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }
    
    public Timestamp getFechaHoraBajaTipoTramite() {
        return fechaHoraBajaTipoTramite;
    }

    public void setFechaHoraBajaTipoTramite(Timestamp fechaHoraBajaTipoTramite) {
        this.fechaHoraBajaTipoTramite = fechaHoraBajaTipoTramite;
    }

    public int getMaxDiaEntrega() {
        return maxDiaEntrega;
    }

    public void setMaxDiaEntrega(int maxDiaEntrega) {
        this.maxDiaEntrega = maxDiaEntrega;
    }

    public String getNombreCategoriaTramite() {
        return nombreCategoriaTramite;
    }

    public void setNombreCategoriaTramite(String nombreCategoriaTramite) {
        this.nombreCategoriaTramite = nombreCategoriaTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }
 
}
