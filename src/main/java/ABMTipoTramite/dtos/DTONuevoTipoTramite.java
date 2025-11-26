package ABMTipoTramite.dtos;

import java.util.ArrayList;
import java.util.List;

public class DTONuevoTipoTramite {
    private int codTipoTramite;
    private int maxDiaEntrega;
    private String nombreCategoriaTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;

    // Lista de documentaciones seleccionadas
    private List<String> listDocumentacion = new ArrayList<>();

    // Getters y Setters
    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
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

    public List<String> getListDocumentacion() {
        return listDocumentacion;
    }

    public void setListDocumentacion(List<String> listDocumentacion) {
        this.listDocumentacion = listDocumentacion;
    }
}
