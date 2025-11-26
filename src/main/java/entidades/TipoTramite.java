package entidades;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TipoTramite extends Entidad {
    private int codTipoTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;
    private int maxDiaEntrega;
    private Timestamp fechaHoraBajaTipoTramite;
    private CategoriaTramite categoriaTramite;
    private List<Documentacion> documentaciones = new ArrayList<>();
    
    // Constructor
    public TipoTramite() {
    }
    
    // Getters y Setters
    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
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
    
    public int getMaxDiaEntrega() {
        return maxDiaEntrega;
    }

    public void setMaxDiaEntrega(int maxDiaEntrega) {
        this.maxDiaEntrega = maxDiaEntrega;
    }

    public Timestamp getFechaHoraBajaTipoTramite() {
        return fechaHoraBajaTipoTramite;
    }

    public void setFechaHoraBajaTipoTramite(Timestamp fechaHoraBajaTipoTramite) {
        this.fechaHoraBajaTipoTramite = fechaHoraBajaTipoTramite;
    }
    
    public CategoriaTramite getCategoriaTramite() {
        return categoriaTramite;
    }
    
    public void setCategoriaTramite (CategoriaTramite categoriaTramite) {
        this.categoriaTramite = categoriaTramite;
    }
    
    public List<Documentacion> getDocumentaciones() {
        return documentaciones;
    }

    public void setDocumentaciones(List<Documentacion> documentaciones) {
        this.documentaciones = documentaciones;
    }
    
    public void addDocumentacion(Documentacion documentacion) {
        this.documentaciones.add(documentacion);
    }
}
