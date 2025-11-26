/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion.bean;

import IniciarSesion.IniciarSesionControlador;
import IniciarSesion.dto.DTOCambioContrasenia;
import IniciarSesion.dto.IniciarSesionExcepcion;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omnifaces.util.Messages;

@Named("uiCambiarContrasenia")
@ViewScoped

public class UICambiarContrasenia implements Serializable{
    private String usuario; 
    private String password;
    private String password2;
    private int codigoValidacion;
    private IniciarSesionControlador userCont = new IniciarSesionControlador() ;

    public UICambiarContrasenia() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        codigoValidacion = Integer.parseInt(request.getParameter("codigoValidacion"));
        
        try {
            usuario=userCont.confirmarNuevaContrasenia(codigoValidacion);
            //DTOCambioContrasenia dtoc = userCont.crearNuevaContrasenia(dtoc);
            
        } catch (IniciarSesionExcepcion ex) {
            Logger.getLogger(UICambiarContrasenia.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(""+codigoValidacion).error().add();
        }
        
    }
    
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

   public void confirmarNuevaContrasenia(int CodValidacion) throws IniciarSesionExcepcion{
       
       usuario=userCont.confirmarNuevaContrasenia(CodValidacion);
       
   }
   
  
    public void crearNuevaContrasenia(){
        try {
           
            DTOCambioContrasenia dtoc = new DTOCambioContrasenia();
                dtoc.setCodValidacion(codigoValidacion);
                dtoc.setPassword(getPassword());
                dtoc.setPassword2(getPassword2());
                dtoc.setUsuario(usuario);
                
            userCont.crearNuevaContrasenia(dtoc);
        } catch (IniciarSesionExcepcion ex) {
            Logger.getLogger(UICambiarContrasenia.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(ex.getMessage()).error().add();
        }
    }
    
}
