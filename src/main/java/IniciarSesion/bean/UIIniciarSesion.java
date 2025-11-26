package IniciarSesion.bean;

import IniciarSesion.IniciarSesionControlador;
import IniciarSesion.dto.DtoIniciarSesion;
import IniciarSesion.dto.IniciarSesionExcepcion;
import com.github.adminfaces.template.session.AdminSession;
import org.omnifaces.util.Faces;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Specializes;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

import com.github.adminfaces.template.config.AdminConfig;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import utils.BeansUtils;

/**
 * Created by rmpestano on 12/20/14.
 *
 * This is just a login example.
 *
 * AdminSession uses isLoggedIn to determine if user must be redirect to login page or not.
 * By default AdminSession isLoggedIn always resolves to true so it will not try to redirect user.
 *
 * If you already have your authorization mechanism which controls when user must be redirect to initial page or logon
 * you can skip this class.
 */
@Named("uiIniciarSesion")

@Specializes
public class UIIniciarSesion extends AdminSession implements Serializable {

    private String nombreUsuario;
    private String usuario;
    private String password;
    private int userCod;
    private String usuarioCamb;
     
     
    private int legajoConsultor;
    private boolean remember;
    @Inject
    private AdminConfig adminConfig;
    private IniciarSesionControlador userCont = new IniciarSesionControlador() ;

    public String getUsuarioCamb() {
        return usuarioCamb;
    }

    public void setUsuarioCamb(String usuarioCamb) {
        this.usuarioCamb = usuarioCamb;
    }

    
    
     
    public void login() throws IOException {
        
        try {
            DtoIniciarSesion dtoi=userCont.findUser(usuario, password);
            nombreUsuario=dtoi.getNombreUsuario();
            userCod=dtoi.getUserCod();
            
            //addDetailMessage("Logged in successfully as <b>" + usuario + "</b>");
            //Faces.getExternalContext().getFlash().setKeepMessages(true);
            Faces.redirect(adminConfig.getIndexPage()); 
        } catch (IniciarSesionExcepcion ex) {
            Logger.getLogger(UIIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            PrimeFaces.current().executeScript("hideBar()");
            Messages.create(ex.getMessage()).error().add();
        }
    }

    @Override
    public boolean isLoggedIn() {
            
        
        return userCod > 0;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getUserCod() {
        return userCod;
    }

    public void setUserCod(int userCod) {
        this.userCod = userCod;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public AdminConfig getAdminConfig() {
        return adminConfig;
    }

    public void setAdminConfig(AdminConfig adminConfig) {
        this.adminConfig = adminConfig;
    }

    public IniciarSesionControlador getUserCont() {
        return userCont;
    }

    public void setUserCont(IniciarSesionControlador userCont) {
        this.userCont = userCont;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }
    
    
    public String irCambiarContrasenia() {
        BeansUtils.guardarUrlAnterior();
        return "cambiarContrasenia.xhtml?faces-redirect=true" ;
    }
    
    public String irACodigo() {
        BeansUtils.guardarUrlAnterior();
        return "validarCodigo.xhtml?faces-redirect=true" ;
    }
    
    public String cambiarContrasenia(){
        
        try {
            userCont.CambioContrasenia(usuarioCamb);
            usuarioCamb="";
            return "validarCodigo.xhtml?faces-redirect=true" ;
          
            
        } catch (IniciarSesionExcepcion ex) {
            Logger.getLogger(UIIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
            
            PrimeFaces.current().executeScript("hideBar()");
            Messages.create(ex.getMessage()).error().add();
            return "";
        }
       
    }
    
   
   
}
