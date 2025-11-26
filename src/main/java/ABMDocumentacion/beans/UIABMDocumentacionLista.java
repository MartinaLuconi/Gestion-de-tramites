package ABMDocumentacion.beans;

import ABMDocumentacion.ControladorABMDocumentacion;
import ABMDocumentacion.dtos.DocumentacionDTO;
import ABMDocumentacion.exceptions.ABMDocumentacionException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

@Named("uiabmDocumentacionLista")  // Nombre del bean para referenciar en la vista
@ViewScoped
public class UIABMDocumentacionLista implements Serializable {

    private ControladorABMDocumentacion controladorABMDocumentacion = new ControladorABMDocumentacion();
    private int codDocumentacionFiltro = 0;
    private String nombreDocumentacionFiltro = "";

    // Getters y Setters
    public ControladorABMDocumentacion getControladorABMDocumentacion() {
        return controladorABMDocumentacion;
    }

    public void setControladorABMDocumentacion(ControladorABMDocumentacion controladorABMDocumentacion) {
        this.controladorABMDocumentacion = controladorABMDocumentacion;
    }

    public int getCodDocumentacionFiltro() {
        return codDocumentacionFiltro;
    }

    public void setCodDocumentacionFiltro(int codDocumentacionFiltro) {
        this.codDocumentacionFiltro = codDocumentacionFiltro;
    }

    public String getNombreDocumentacionFiltro() {
        return nombreDocumentacionFiltro;
    }

    public void setNombreDocumentacionFiltro(String nombreDocumentacionFiltro) {
        this.nombreDocumentacionFiltro = nombreDocumentacionFiltro;
    }

    // Método para buscar documentaciones aplicando filtros y ordenarlas por codDocumentacion
    public List<DocumentacionGrillaUI> buscarDocumentaciones() {
        List<DocumentacionGrillaUI> documentacionesGrilla = new ArrayList<>();
        List<DocumentacionDTO> documentacionesDTO = controladorABMDocumentacion.buscarDocumentacion(codDocumentacionFiltro, nombreDocumentacionFiltro);

        if (documentacionesDTO != null) {
            for (DocumentacionDTO documentacionDTO : documentacionesDTO) {
                DocumentacionGrillaUI documentacionGrillaUI = new DocumentacionGrillaUI();
                documentacionGrillaUI.setCodDocumentacion(documentacionDTO.getCodDocumentacion());
                documentacionGrillaUI.setNombreDocumentacion(documentacionDTO.getNombreDocumentacion());
                documentacionGrillaUI.setFechaHoraBajaDocumentacion(documentacionDTO.getFechaHoraBajaDocumentacion());
                documentacionesGrilla.add(documentacionGrillaUI);
            }

            // Ordenar la lista por codDocumentacion
            documentacionesGrilla.sort(Comparator.comparing(DocumentacionGrillaUI::getCodDocumentacion));
        }

        return documentacionesGrilla;
    }


    // Redirigir a la página para agregar una nueva documentación
    public String irAgregarDocumentacion() {
        BeansUtils.guardarUrlAnterior();
        return "abmDocumentacion.xhtml?faces-redirect=true&codigo=0";
    }

    // Redirigir a la página para modificar una documentación existente
    public String irModificarDocumentacion(int codDocumentacion) {
        BeansUtils.guardarUrlAnterior();
        return "abmDocumentacion?faces-redirect=true&codigo=" + codDocumentacion;
    }

    // Método para dar de baja una documentación
    public void darDeBajaDocumentacion(int codDocumentacion) {
        try {
        controladorABMDocumentacion.darDeBajaDocumentacion(codDocumentacion);
        Messages.create("Anulado").detail("Anulado").add();
          } catch (ABMDocumentacionException e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
    
}
