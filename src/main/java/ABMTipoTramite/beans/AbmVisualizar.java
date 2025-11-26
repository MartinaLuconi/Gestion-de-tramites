package ABMTipoTramite.beans;

import ABMTipoTramite.ControladorABMTipoTramite;
import ABMTipoTramite.dtos.DTODocumentacionTT;
import ABMTipoTramite.dtos.DTOVerTipoTramite;
import ABMTipoTramite.exceptions.ABMTipoTramiteException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("AbmVisualizarTT")
@ViewScoped
public class AbmVisualizar implements Serializable {
    private ControladorABMTipoTramite controladorABMTipoTramite = new ControladorABMTipoTramite();
    private int codTipoTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;
    private String categoriaTramite;
    private int maxDiaEntrega;
    private String documentacion;

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

    public int getMaxDiaEntrega() {
        return maxDiaEntrega;
    }

    public void setMaxDiaEntrega(int maxDiaEntrega) {
        this.maxDiaEntrega = maxDiaEntrega;
    }

    // Getter y Setter para codTipoTramite (¡Necesarios para f:viewParam!)
    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    // Otros getters y setters
    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public String getDescripcionTipoTramite() {
        return descripcionTipoTramite;
    }

    public String getDescripcionTipoTramiteWeb() {
        return descripcionTipoTramiteWeb;
    }

 

    // Método para cargar los datos basado en el código del trámite
 public void cargarDatos() {
    try {
        DTOVerTipoTramite dto = controladorABMTipoTramite.visualizarTipoTramite(codTipoTramite);
        this.codTipoTramite = dto.getCodTipoTramite();
        this.nombreTipoTramite = dto.getNombreTipoTramite();
        this.descripcionTipoTramite = dto.getDescripcionTipoTramite();
        this.descripcionTipoTramiteWeb = dto.getDescripcionTipoTramiteWeb();
        this.categoriaTramite = dto.getNombreCategoriaTramite();
        this.maxDiaEntrega = dto.getMaxDiaEntrega();
        
        // Obtener y manejar la lista de documentaciones seleccionadas
        List<DTODocumentacionTT> documentaciones = dto.getListaDocumentacion();
        if (documentaciones != null && !documentaciones.isEmpty()) {
            this.documentacion = documentaciones.stream()
                .map(DTODocumentacionTT::getNombreDocumentacion)
                .collect(Collectors.joining(", "));
        } else {
            this.documentacion = "No hay documentación seleccionada.";
        }
    } catch (ABMTipoTramiteException e) {
        e.printStackTrace();
    }
}

}



