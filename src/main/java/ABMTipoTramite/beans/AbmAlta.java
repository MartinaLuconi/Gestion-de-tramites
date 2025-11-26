package ABMTipoTramite.beans;

import ABMTipoTramite.ControladorABMTipoTramite;
import ABMTipoTramite.dtos.DTOCategoriaTramite;
import ABMTipoTramite.dtos.DTODocumentacionTT;
import ABMTipoTramite.dtos.DTONuevoTipoTramite;
import ABMTipoTramite.exceptions.ABMTipoTramiteException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("AbmAltaTT")
@ViewScoped
public class AbmAlta implements Serializable {
    private ControladorABMTipoTramite controladorABMTipoTramite = new ControladorABMTipoTramite();
    private boolean insert = true;
    private int codTipoTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;
    private int maxDiaEntrega;
    private Timestamp fechaHoraBajaTipoTramite;
    private String categoriaTramite;

    // Cambiar a lista para seleccionar múltiples documentaciones
    private List<String> documentacionesSeleccionadas = new ArrayList<>();
    private List<DTOCategoriaTramite> categoriasPosibles = new ArrayList<>();
    private List<DTODocumentacionTT> documentacionesPosibles = new ArrayList<>();

    public AbmAlta() {
        categoriasPosibles = controladorABMTipoTramite.buscarCategoriasPosibles();
        documentacionesPosibles = controladorABMTipoTramite.buscarDocumentacionesPosibles();
    }

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

    public String getCategoriaTramite() {
        return categoriaTramite;
    }

    public void setCategoriaTramite(String categoriaTramite) {
        this.categoriaTramite = categoriaTramite;
    }

    public List<String> getDocumentacionesSeleccionadas() {
        return documentacionesSeleccionadas;
    }

    public void setDocumentacionesSeleccionadas(List<String> documentacionesSeleccionadas) {
        this.documentacionesSeleccionadas = documentacionesSeleccionadas;
    }

    public List<DTOCategoriaTramite> getCategoriasPosibles() {
        return categoriasPosibles;
    }

    public void setCategoriasPosibles(List<DTOCategoriaTramite> categoriasPosibles) {
        this.categoriasPosibles = categoriasPosibles;
    }

    public List<DTODocumentacionTT> getDocumentacionesPosibles() {
        return documentacionesPosibles;
    }

    public void setDocumentacionesPosibles(List<DTODocumentacionTT> documentacionesPosibles) {
        this.documentacionesPosibles = documentacionesPosibles;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public String darDeAlta() {
        DTONuevoTipoTramite pantalla = new DTONuevoTipoTramite();
        pantalla.setCodTipoTramite(codTipoTramite);
        pantalla.setDescripcionTipoTramite(descripcionTipoTramite);
        pantalla.setDescripcionTipoTramiteWeb(descripcionTipoTramiteWeb);
        pantalla.setMaxDiaEntrega(maxDiaEntrega);
        pantalla.setNombreCategoriaTramite(categoriaTramite);
        pantalla.setNombreTipoTramite(nombreTipoTramite);
        pantalla.setListDocumentacion(documentacionesSeleccionadas); // Asignar la lista de documentaciones seleccionadas

        try {
            controladorABMTipoTramite.agregarTipoTramite(pantalla);
            Messages.addGlobalInfo("Se dio de alta con éxito");
        } catch (ABMTipoTramiteException ex) {
            Logger.getLogger(AbmAlta.class.getName()).log(Level.SEVERE, null, ex);
            Messages.addGlobalError("Error al dar de alta: " + ex.getMessage());
        }

        return null; // Cambiar a null para permanecer en la misma vista
    }
}
