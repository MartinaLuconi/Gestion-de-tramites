package AMBConsultor.beans;

import AMBConsultor.ControladorABMConsultor;
import AMBConsultor.dtos.ModificarConsultorDTO;
import AMBConsultor.dtos.ModificarConsultorDTOIn;
import AMBConsultor.dtos.NuevoConsultorDTO;
import AMBConsultor.exceptions.ConsultorException;
import entidades.Usuario;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import org.omnifaces.util.Messages;
import utils.BeansUtils;


/**
 *
 * @author adrie
 */

@Named("uiabmConsultor")  
@ViewScoped 
public class UIABMConsultor implements Serializable  {
    
    private ControladorABMConsultor controladorAMBConsultor = new ControladorABMConsultor();
    private boolean insert;
    private String nombreApellidoConsultor;
    private int legajoConsultor;
    private int consultorNroMaxTramite;
    private String usuario;

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public String getNombreApellidoConsultor() {
        return nombreApellidoConsultor;
    }

    public void setNombreApellidoConsultor(String nombreApellidoConsultor) {
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }
    
    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }

    public int getConsultorNroMaxTramite() {
        return consultorNroMaxTramite;
    }

   public void setConsultorNroMaxTramite(int consultorNroMaxTramite) {
        this.consultorNroMaxTramite = consultorNroMaxTramite;
    }
   
   public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public UIABMConsultor() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        int legajo = Integer.parseInt(request.getParameter("legajo"));
        insert = true;
        if (legajo > 0) 
        {
            insert = false;
            ModificarConsultorDTO modificarConsultorDTO = controladorAMBConsultor.buscarConsultorAModificar(legajo);
            setNombreApellidoConsultor(modificarConsultorDTO.getNombreApellidoConsultor());
            setUsuario(modificarConsultorDTO.getUsuario());
            setLegajoConsultor(modificarConsultorDTO.getLegajoConsultor());
            setConsultorNroMaxTramite(modificarConsultorDTO.getConsultorNroMaxTramite());
        }
    }

     public String agregarModificarConsultor() {
    try {
        if (insert) {
            // Crear un nuevo consultor
            NuevoConsultorDTO nuevoConsultorDTO = new NuevoConsultorDTO();
            nuevoConsultorDTO.setLegajoConsultor(getLegajoConsultor());
            nuevoConsultorDTO.setNombreApellidoConsultor(getNombreApellidoConsultor());
            nuevoConsultorDTO.setUsuario(getUsuario());
            nuevoConsultorDTO.setConsultorNroMaxTramite(getConsultorNroMaxTramite());

            controladorAMBConsultor.agregarConsultor(nuevoConsultorDTO);
        } else {
            // Modificar un consultor existente
            ModificarConsultorDTOIn modificarConsultorDTOIn = new ModificarConsultorDTOIn();
            modificarConsultorDTOIn.setLegajoConsultor(getLegajoConsultor());
            modificarConsultorDTOIn.setNombreApellidoConsultor(getNombreApellidoConsultor());
            modificarConsultorDTOIn.setConsultorNroMaxTramite(getConsultorNroMaxTramite());
            modificarConsultorDTOIn.setUsuario(getUsuario());
            controladorAMBConsultor.modificarConsultor(modificarConsultorDTOIn);
        }
        return BeansUtils.redirectToPreviousPage();
    } catch (ConsultorException e) {
        Messages.create(e.getMessage()).fatal().add();
        return "";
        }   
    }
    
     public String volverALista() {
        return "abmConsultorLista.xhtml?faces-redirect=true";
    }
}
