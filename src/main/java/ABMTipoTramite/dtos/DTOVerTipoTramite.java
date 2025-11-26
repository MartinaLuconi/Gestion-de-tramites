package ABMTipoTramite.dtos;

import java.util.List;

public class DTOVerTipoTramite {
    private int codTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;
    private int maxDiaEntrega;
    private int codCategoriaTramite;
    private String nombreTipoTramite;
    private String nombreCategoriaTramite;
    
    // Lista para almacenar las documentaciones seleccionadas
    private List<DTODocumentacionTT> listaDocumentacion;

    // Getters y Setters

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

    public String getDescripcionTipoTramiteWeb() {
        return descripcionTipoTramiteWeb;
    }

    public void setDescripcionTipoTramiteWeb(String descripcionTipoTramiteWeb) {
        this.descripcionTipoTramiteWeb = descripcionTipoTramiteWeb;
    }

    public int getMaxDiaEntrega() {
        return maxDiaEntrega;
    }

    public void setMaxDiaEntrega(int maxDiaEntrega) {
        this.maxDiaEntrega = maxDiaEntrega;
    }

    public int getCodCategoriaTramite() {
        return codCategoriaTramite;
    }

    public void setCodCategoriaTramite(int codCategoriaTramite) {
        this.codCategoriaTramite = codCategoriaTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public String getNombreCategoriaTramite() {
        return nombreCategoriaTramite;
    }

    public void setNombreCategoriaTramite(String nombreCategoriaTramite) {
        this.nombreCategoriaTramite = nombreCategoriaTramite;
    }

    // Getter y Setter para listaDocumentacion
    public List<DTODocumentacionTT> getListaDocumentacion() {
        return listaDocumentacion;
    }

    public void setListaDocumentacion(List<DTODocumentacionTT> listaDocumentacion) {
        this.listaDocumentacion = listaDocumentacion;
    }
}
