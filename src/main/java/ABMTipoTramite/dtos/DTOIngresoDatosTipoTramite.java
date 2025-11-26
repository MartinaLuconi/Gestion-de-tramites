
package ABMTipoTramite.dtos;

import java.sql.Timestamp;

public class DTOIngresoDatosTipoTramite {
    private int codCategoriaTipoTramite;
    private int codDocumentacion;
    private int codTipoTramite;
    private String descripcionTipoTramite;
    private String nombreTipoTramite;
    private Timestamp fechaHoraBajaTipoTramite;
    private Timestamp maxDiaEntrega;

    public int getCodCategoriaTipoTramite() {
        return codCategoriaTipoTramite;
    }

    public void setCodCategoriaTipoTramite(int codCategoriaTipoTramite) {
        this.codCategoriaTipoTramite = codCategoriaTipoTramite;
    }

    public int getCodDocumentacion() {
        return codDocumentacion;
    }

    public void setCodDocumentacion(int codDocumentacion) {
        this.codDocumentacion = codDocumentacion;
    }

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    public String getDescripcionTipoTramite() {
        return descripcionTipoTramite;
    }

    public void setDescripcionTipoTramite(String descripcionTipoTramite) {
        this.descripcionTipoTramite = descripcionTipoTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public Timestamp getFechaHoraBajaTipoTramite() {
        return fechaHoraBajaTipoTramite;
    }

    public void setFechaHoraBajaTipoTramite(Timestamp fechaHoraBajaTipoTramite) {
        this.fechaHoraBajaTipoTramite = fechaHoraBajaTipoTramite;
    }

    public Timestamp getMaxDiaEntrega() {
        return maxDiaEntrega;
    }

    public void setMaxDiaEntrega(Timestamp maxDiaEntrega) {
        this.maxDiaEntrega = maxDiaEntrega;
    }
    
    
}
