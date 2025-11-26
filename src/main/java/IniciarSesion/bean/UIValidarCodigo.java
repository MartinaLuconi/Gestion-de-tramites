/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion.bean;

import IniciarSesion.IniciarSesionControlador;
import IniciarSesion.dto.IniciarSesionExcepcion;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

/**
 *
 * @author marti
 */


@Named("uiValidarCodigo")
@ViewScoped
public class UIValidarCodigo implements Serializable{
    private int codigoValidacion;
    private int codValidacionIn;
    private IniciarSesionControlador userCont = new IniciarSesionControlador() ;

    public UIValidarCodigo() {
        /*
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        codigoValidacion = Integer.parseInt(request.getParameter("codigoValidacion"));*/
    }

    public int getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(int codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public int getCodValidacionIn() {
        return codValidacionIn;
    }

    public void setCodValidacionIn(int codValidacionIn) {
        this.codValidacionIn = codValidacionIn;
    }

    public IniciarSesionControlador getUserCont() {
        return userCont;
    }

    public void setUserCont(IniciarSesionControlador userCont) {
        this.userCont = userCont;
    }
    
    public String irCambiarContrasenia() {
        BeansUtils.guardarUrlAnterior();
        return "validacionContrasenia.xhtml?faces-redirect=true&codigoValidacion="+ codigoValidacion ; //agregar el codigo para pasarlo como parametro
    }
    
    public String validarCodigo(){
         boolean validacion = false;
           try {
            String usuario=userCont.confirmarNuevaContrasenia(codValidacionIn);
            //DTOCambioContrasenia dtoc = userCont.crearNuevaContrasenia(dtoc);
            BeansUtils.guardarUrlAnterior();
                return "validacionContrasenia.xhtml?faces-redirect=true&codigoValidacion="+ codValidacionIn ; //agregar el codigo para pasarlo como parametro
            
        } catch (IniciarSesionExcepcion ex) {
            Logger.getLogger(UICambiarContrasenia.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(""+ex.getMessage()).error().add();
            return "";
        }
        
        
    }

  
    
}
