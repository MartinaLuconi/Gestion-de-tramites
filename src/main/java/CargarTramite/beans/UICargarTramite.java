package CargarTramite.beans;
import CargarTramite.ControladorCargarTramite;
import CargarTramite.dtos.DTOClienteV;
import CargarTramite.dtos.DTOTipoTramiteV; 
import CargarTramite.exceptions.TramiteException;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;


@Named("uicargarTramite")
@ViewScoped
public class UICargarTramite implements Serializable {


    private String cuit;
    private int codigoTT;
    private CargarTramiteGrillaUI cargarTramiteGrilla = new CargarTramiteGrillaUI();
    private ControladorCargarTramite controladorCargarTramite = new ControladorCargarTramite();
    
    
    public UICargarTramite() {
    }

    
    public CargarTramiteGrillaUI obtenerCliente(String cuit) {
        try {
            DTOClienteV dtoCliente = controladorCargarTramite.obtenerCliente(cuit);
            cargarTramiteGrilla.setCuit(dtoCliente.getCUIT());
            cargarTramiteGrilla.setNombreCliente(dtoCliente.getNombreApellidoCliente());
            cargarTramiteGrilla.setDireccion(dtoCliente.getDireccion());
            System.out.println(cargarTramiteGrilla.getCuit());
            System.out.println(cargarTramiteGrilla.getNombreCliente());
            System.out.println(cargarTramiteGrilla.getDireccion());
        } catch (TramiteException e) {
            cargarTramiteGrilla.setCuit(null);
            cargarTramiteGrilla.setNombreCliente(null);
            cargarTramiteGrilla.setDireccion(null);
            Messages.create("Error").error().detail(e.getMessage()).add();
        }
        return cargarTramiteGrilla;
    }

    
    
        public CargarTramiteGrillaUI obtenerTipoTramite(int codigoTT) {
        try {
             DTOTipoTramiteV dtoTT = controladorCargarTramite.obtenerTipoTramite(codigoTT);
             cargarTramiteGrilla.setCodigo(dtoTT.getCodigoTT());
             cargarTramiteGrilla.setNombreTipoTramite(dtoTT.getNombreTipoTramite());
             return cargarTramiteGrilla;
        } catch (TramiteException e) {
            cargarTramiteGrilla.setCodigo(0);
            cargarTramiteGrilla.setNombreTipoTramite(null);
        Messages.create("Error").error().detail(e.getMessage()).add();
        }
        return cargarTramiteGrilla;
        }
        

        
        public String cancelar(){
            return "mostrarTramitesLista?redirect=true";
        }
        
 
        
        public void botonCargar(String cuit, int codigoTT) throws TramiteException, Exception{
            boolean exito = false;
        try {
            controladorCargarTramite.botonCargar(cuit, codigoTT);
            exito = true;
            
        } catch (TramiteException e) {
            Messages.create("Error: ").error().detail(e.getMessage()).add();
            PrimeFaces.current().ajax().update("formBoton"); 
            } 
        
        if(exito){
            FacesContext.getCurrentInstance().getExternalContext().redirect("mostrarTramitesLista.xhtml");
        }
        
        
    }
        
    
    // Getters y setters
 
    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public int getCodigoTT() {
        return codigoTT;
    }

    public void setCodigoTT(int codigoTT) {
        this.codigoTT = codigoTT;
    }

    public CargarTramiteGrillaUI getCargarTramiteGrilla() {
        return cargarTramiteGrilla;
    }

    public void setCargarTramiteGrilla(CargarTramiteGrillaUI cargarTramiteGrilla) {
        this.cargarTramiteGrilla = cargarTramiteGrilla;
    }

    public ControladorCargarTramite getControladorCargarTramite() {
        return controladorCargarTramite;
    }

    public void setControladorCargarTramite(ControladorCargarTramite controladorCargarTramite) {
        this.controladorCargarTramite = controladorCargarTramite;
    }
    
}

