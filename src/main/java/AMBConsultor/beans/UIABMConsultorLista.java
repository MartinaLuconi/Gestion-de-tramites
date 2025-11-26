package AMBConsultor.beans;

import utils.BeansUtils;
import AMBConsultor.ControladorABMConsultor;
import AMBConsultor.dtos.ConsultorDTO;
import AMBConsultor.exceptions.ConsultorException;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;  

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.omnifaces.util.Messages;

@Named("uiabmConsultorLista")  // Este nombre debe coincidir con el que usas en el archivo XHTML
@ViewScoped  // Asegúrate de tener el alcance correcto
public class UIABMConsultorLista implements Serializable {
    
    private ControladorABMConsultor controladorAMBConsultor = new ControladorABMConsultor();
    private int legajoConsultorFiltro = 0;
    private String nombreApellidoConsultorFiltro = "";

    // Getters y Setters
    public ControladorABMConsultor getControladorAMBConsultor() {
        return controladorAMBConsultor;
    }

    public void setControladorAMBConsultor(ControladorABMConsultor controladorAMBConsultor) {
        this.controladorAMBConsultor = controladorAMBConsultor;
    }

    public int getLegajoConsultorFiltro() {
        return legajoConsultorFiltro;
    }

    public void setLegajoConsultorFiltro(int legajoConsultorFiltro) {
        this.legajoConsultorFiltro = legajoConsultorFiltro;
    }

    public String getNombreApellidoConsultorFiltro() {
        return nombreApellidoConsultorFiltro;
    }

    public void setNombreApellidoConsultorFiltro(String nombreApellidoConsultorFiltro) {
        this.nombreApellidoConsultorFiltro = nombreApellidoConsultorFiltro;
    }

    // Método para buscar consultores aplicando filtros y ordenarlos por legajo
    public List<ConsultorGrillaUI> buscarConsultores() {
        List<ConsultorGrillaUI> consultoresGrilla = new ArrayList<>(); // Inicializar lista vacía
        List<ConsultorDTO> consultoresDTO = controladorAMBConsultor.buscarConsultores(legajoConsultorFiltro, nombreApellidoConsultorFiltro);

        if (consultoresDTO != null) {
            for (ConsultorDTO consultorDTO : consultoresDTO) {
                ConsultorGrillaUI consultorGrillaUI = new ConsultorGrillaUI();
                consultorGrillaUI.setLegajoConsultor(consultorDTO.getLegajoConsultor());
                consultorGrillaUI.setNombreApellidoConsultor(consultorDTO.getNombreApellidoConsultor());
                consultorGrillaUI.setConsultorNroMaxTramite(consultorDTO.getConsultorNroMaxTramite());
                consultorGrillaUI.setFechaHoraBajaConsultor(consultorDTO.getFechaHoraBajaConsultor());
                consultoresGrilla.add(consultorGrillaUI);
            }
            
            // Ordenar la lista por legajoConsultor
            consultoresGrilla.sort(Comparator.comparing(ConsultorGrillaUI::getLegajoConsultor));
        }

        return consultoresGrilla;
    }


    // Redirigir a la página para agregar un nuevo consultor
    public String irAgregarConsultor() {
        BeansUtils.guardarUrlAnterior();
        return "abmConsultor.xhtml?faces-redirect=true&legajo=0";
    }

    // Redirigir a la página para modificar un consultor existente
    public String irModificarConsultor(int legajoConsultor) {
        BeansUtils.guardarUrlAnterior();
        return "abmConsultor?faces-redirect=true&legajo=" + legajoConsultor;
    }

    // Método para dar de baja un consultor
    public void darDeBajaConsultor(int legajoConsultor) {
        try {
            controladorAMBConsultor.darDeBajaConsultor(legajoConsultor);
            Messages.create("Anulado").detail("Anulado").add();
          } catch (ConsultorException e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
    
}
