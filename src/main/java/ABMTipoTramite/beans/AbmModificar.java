package ABMTipoTramite.beans;

import ABMTipoTramite.ControladorABMTipoTramite;
import ABMTipoTramite.dtos.DTOCategoriaTramite;
import ABMTipoTramite.dtos.DTODocumentacionTT;
import ABMTipoTramite.dtos.DTOModificarTipoTramite;
import ABMTipoTramite.dtos.DTOModificarTipoTramiteIn;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;

@Named("AbmModificarTT")
@ViewScoped
public class AbmModificar implements Serializable {
    private ControladorABMTipoTramite controladorABMTipoTramite = new ControladorABMTipoTramite();
    private boolean insert;
    private int codTipoTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;
    private int maxDiaEntrega;
    private Timestamp fechaHoraBajaTipoTramite;
    private String categoriaTramite;
    private List<String> documentacionesSeleccionadas = new ArrayList<>();
    private List<DTOCategoriaTramite> categoriasPosibles = new ArrayList<>();
    private List<DTODocumentacionTT> documentacionesPosibles = new ArrayList<>();

    // Getters y Setters
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

    public List<String> getDocumentacionesSeleccionadas() {
        return documentacionesSeleccionadas;
    }

    public void setDocumentacionesSeleccionadas(List<String> documentacionesSeleccionadas) {
        this.documentacionesSeleccionadas = documentacionesSeleccionadas;
    }

    public String getCategoriaTramite() {
        return categoriaTramite;
    }

    public void setCategoriaTramite(String categoriaTramite) {
        this.categoriaTramite = categoriaTramite;
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

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public Timestamp getFechaHoraBajaTipoTramite() {
        return fechaHoraBajaTipoTramite;
    }

    public void setFechaHoraBajaTipoTramite(Timestamp fechaHoraBajaTipoTramite) {
        this.fechaHoraBajaTipoTramite = fechaHoraBajaTipoTramite;
    }

    // Constructor
    public AbmModificar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        codTipoTramite = codigo; 
        
        insert = false; 
        DTOModificarTipoTramite dto = controladorABMTipoTramite.buscarTipoTramiteAModificar(codigo);
        
        if (dto != null) {
            nombreTipoTramite = dto.getNombreTipoTramite();
            descripcionTipoTramite = dto.getDescripcionTipoTramite();
            descripcionTipoTramiteWeb = dto.getDescripcionTipoTramiteWeb();
            maxDiaEntrega = dto.getMaxDiaEntrega();
            fechaHoraBajaTipoTramite = dto.getFechaHoraBajaTipoTramite();
            categoriaTramite = dto.getNombreCategoriaTramite();
            
            // Cargar documentaciones seleccionadas previamente
            for (DTODocumentacionTT doc : dto.getListDocumentacion()) {
                documentacionesSeleccionadas.add(doc.getNombreDocumentacion());
            }
        }
        
        // Cargar categorías posibles
        List<DTOCategoriaTramite> busquedaCat = controladorABMTipoTramite.buscarCategoriasPosibles();
        categoriasPosibles.addAll(busquedaCat);

        // Cargar documentaciones posibles
        documentacionesPosibles = controladorABMTipoTramite.buscarDocumentacionesPosibles();
    }

    public void modificarTipoTramite() {
        try {
            DTOModificarTipoTramiteIn pantalla = new DTOModificarTipoTramiteIn();
            pantalla.setCodTipoTramite(codTipoTramite);
            pantalla.setNombreTipoTramite(nombreTipoTramite);
            pantalla.setDescripcionTipoTramite(descripcionTipoTramite);
            pantalla.setDescripcionTipoTramiteWeb(descripcionTipoTramiteWeb);
            pantalla.setMaxDiaEntrega(maxDiaEntrega);
            pantalla.setNombreCategoriaTramite(categoriaTramite);
            pantalla.setDocumentacionesSeleccionadas(documentacionesSeleccionadas); // Asignar las documentaciones seleccionadas

            controladorABMTipoTramite.modificarTipoTramite(pantalla);
            Messages.addGlobalInfo("Tipo de trámite modificado correctamente.");
        } catch (Exception e) {
            Messages.addGlobalError("Error al modificar el tipo de trámite: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
