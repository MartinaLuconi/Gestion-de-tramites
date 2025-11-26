
package ABMTipoTramite.dtos;

import entidades.Documentacion;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DTOModificarTipoTramite {
    
    private int codTipoTramite;
    private Timestamp fechaHoraBajaTipoTramite;
    private int maxDiaEntrega;
    private String nombreCategoriaTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;
    private String nombreDocumentacion;
    private List<DTODocumentacionTT> listDocumentacion =new ArrayList();

    public String getNombreDocumentacion() {
        return nombreDocumentacion;
    }

    public void setNombreDocumentacion(String nombreDocumentacion) {
        this.nombreDocumentacion = nombreDocumentacion;
    }
    
    public void setDescripcionTipoTramiteWeb(String descripcionTipoTramiteWeb) {
        this.descripcionTipoTramiteWeb = descripcionTipoTramiteWeb;
    }

    public String getDescripcionTipoTramiteWeb() {
        return descripcionTipoTramiteWeb;
    }

    public String getDescripcionTipoTramite() {
        return descripcionTipoTramite;
    }

    public void setDescripcionTipoTramite(String descripcionTipoTramite) {
        this.descripcionTipoTramite = descripcionTipoTramite;
    }

    public List<DTODocumentacionTT> getListDocumentacion() {
        return listDocumentacion;
    }

    public void setListDocumentacion(List<DTODocumentacionTT> listDocumentacion) {
        this.listDocumentacion = listDocumentacion;
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
