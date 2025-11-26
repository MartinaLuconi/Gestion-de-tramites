
package ABMTipoTramite.beans;

import ABMTipoTramite.ControladorABMTipoTramite;
import ABMTipoTramite.dtos.DTOTipoTramite;
import ABMTipoTramite.exceptions.ABMTipoTramiteException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

@Named("UIABMTipoTramiteLista")
@ViewScoped

public class UIABMTipoTramiteLista implements Serializable{
    
    private ControladorABMTipoTramite controladorABMTipoTramite = new ControladorABMTipoTramite();
    private int codigoFiltro=0;
    private String nombreFiltro="";
    private int codigoTT;

    public int getCodigoTT() {
        return codigoTT;
    }

    public void setCodigoTT(int codigoTT) {
        this.codigoTT = codigoTT;
    }

    public ControladorABMTipoTramite ControladorABMTipoTramite() {
        return controladorABMTipoTramite;
    }

    public void setControladorABMTipoTramite(ControladorABMTipoTramite controladorABMTipoTramite) {
        this.controladorABMTipoTramite = controladorABMTipoTramite;
    }

    public int getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(int codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public String getNombreFiltro() {
        return nombreFiltro;
    }

    public void setNombreFiltro(String descripcionFiltro) {
        this.nombreFiltro = descripcionFiltro;
    }
   

    public List<DTOTipoTramite> buscarTipoTramites(){

        List<DTOTipoTramite> DTOTipoTramites = controladorABMTipoTramite.buscarTipoTramites(codigoFiltro,nombreFiltro);
        
        return DTOTipoTramites;
    }

    public String irAgregarTipoTramite() {
        BeansUtils.guardarUrlAnterior();
        return "/ABMTipoTramite/abmTTAlta?faces-redirect=true"; // Usa '?faces-redirect=true' para hacer una redirección
    }

    
    public String irModificarTipoTramite(int codigo) {
        BeansUtils.guardarUrlAnterior();
        return "/ABMTipoTramite/abmTTModificar?faces-redirect=true&codigo=" + codigo; // Usa '?faces-redirect=true' para hacer una redirección
    }

    public void darDeBajaTipoTramite(int codigo){
        try {
            controladorABMTipoTramite.darDeBajaTipoTramite(codigo);
            Messages.create("Anulado").detail("Anulado").add();
                    
        } catch (ABMTipoTramiteException e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
public String irVisualizarTipoTramite(int codigo) {
    // Redirige a la página de visualización con el código del trámite como parámetro
    return "/ABMTipoTramite/abmTTVisualizar?faces-redirect=true&codigo=" + codigo;
}
public  String irConfigurarVersiones() {
        //List<DTOVersionHistorial> listResultado = controlador.historialVersiones(codigo);
        //BeansUtils.guardarUrlAnterior();
        return "/ABMConfigurarVersiones/VersionGrillaUI.xhtml?faces-redirect=true"; // Usa '?faces-redirect=true' para hacer una redirección
      }

    
    
}
