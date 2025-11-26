package SolicitarTramiteWeb.beans;


import SolicitarTramiteWeb.dtos.DTOCategoriaTramite;
import SolicitarTramiteWeb.dtos.DTOCliente;
import SolicitarTramiteWeb.dtos.DTOConfirmacion2;
import SolicitarTramiteWeb.dtos.DTODocumentacion;
import SolicitarTramiteWeb.dtos.DTOTipoTramiteWeb;
import SolicitarTramiteWeb.dtos.DTOTramiteWeb;
import SolicitarTramiteWeb.exceptions.ControladorSolicitarTramiteWeb;
import com.google.gson.Gson;
import entidades.Documentacion;
import entidades.EstadoTramite;
import entidades.Tramite;
import entidades.TramiteDocumentacion;
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
import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;

@Named("uiSolicitarTramiteWeb")
@ViewScoped

public class UISolicitarTramiteWeb implements Serializable {
    
    private ControladorSolicitarTramiteWeb controladorSolicitarTramiteWeb = new ControladorSolicitarTramiteWeb();
    
    private String cuitCliente;
    private String direccionCliente;
    private String nombreApellidoCliente;
    private String mailCliente;
    private Timestamp fechaHoraBajaCliente;
    private int codCategoriaTramite;
    private String nombreCategoriaTramite;
    private int codTipoTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private float precioTramite;
    private UITramite tramite=new UITramite();
    

    
   
 
   
    
    
    public UITramite getTramite() {
        return tramite;
    }

    public void setTramite(UITramite tramite) {
        this.tramite = tramite;
    }

  
    public float getPrecioTramite() {
        return precioTramite;
    }

    public void setPrecioTramite(float precioTramite) {
        this.precioTramite = precioTramite;
    }
    
    

    public String getDescripcionTipoTramite() {
        return descripcionTipoTramite;
    }

    public void setDescripcionTipoTramite(String descripcionTipoTramite) {
        this.descripcionTipoTramite = descripcionTipoTramite;
    }
    
    

    public ControladorSolicitarTramiteWeb getControladorSolicitarTramiteWeb() {
        return controladorSolicitarTramiteWeb;
    }

    public void setControladorSolicitarTramiteWeb(ControladorSolicitarTramiteWeb controladorSolicitarTramiteWeb) {
        this.controladorSolicitarTramiteWeb = controladorSolicitarTramiteWeb;
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
    

    public int getCodCategoriaTramite() {
        return codCategoriaTramite;
    }

    public Timestamp getFechaHoraBajaCliente() {
        return fechaHoraBajaCliente;
    }

    public void setFechaHoraBajaCliente(Timestamp fechaHoraBajaCliente) {
        this.fechaHoraBajaCliente = fechaHoraBajaCliente;
    }

    public void setCodCategoriaTramite(int codCategoriaTramite) {
        this.codCategoriaTramite = codCategoriaTramite;
    }

    public String getNombreCategoriaTramite() {
        return nombreCategoriaTramite;
    }

    public void setNombreCategoriaTramite(String nombreCategoriaTramite) {
        this.nombreCategoriaTramite = nombreCategoriaTramite;
    }
    
    public String getMailCliente() {
        return mailCliente;
    }

    public void setMailCliente(String mailCliente) {
        this.mailCliente = mailCliente;
    }

    public String getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    
    
    public String onFlowProcess(FlowEvent event) throws Exception {
        System.out.println("old"+event.getOldStep());
        System.out.println("new"+event.getNewStep());
        DTOCliente dtoC;
        PrimeFaces.current().executeScript("a=$(\".ui-dataview-header\");a.css('visibility', 'hidden');a.css('height', '0');");
        if(event.getOldStep().compareTo("validaciondeid")==0)
        {
            dtoC=controladorSolicitarTramiteWeb.obtenerCliente(this.cuitCliente);
           if(dtoC != null)
           {
            this.nombreApellidoCliente=dtoC.getNombreApellidoCliente();
            this.direccionCliente=dtoC.getDireccionCliente();
            this.mailCliente=dtoC.getMailCliente();
            
            return event.getNewStep();
            
           }
           else
           {
               this.nombreApellidoCliente="";
               Messages.create("No se encontró el cliente ingresado").fatal().add();
               return event.getOldStep();        
           }
        }
        else if(event.getOldStep().compareTo("tramite")==0)
        {
                     
//                String cuitInt=Integer.parseInt(cuitCliente); Esto no es necesario porque ya es un String
                
            DTOTramiteWeb dTramite = controladorSolicitarTramiteWeb.elegirTipoTramite(codTipoTramite,cuitCliente);
            tramite.setCodigoo(dTramite.getCodTipoTramite());
            tramite.setPrecioTipoTramite(dTramite.getPrecioTramite());
            tramite.setDescripcionn(dTramite.getDescripcionTipoTramiteWeb());
            tramite.setNombree(dTramite.getNombreTipoTramite());
            tramite.setDTOdocumentacion(dTramite.getDocumentacionList());
           
            
            DTOConfirmacion2 confirmacion =controladorSolicitarTramiteWeb.confirmar2();
            
            tramite.setNroTramite(confirmacion.getNroTramite());
            tramite.setNombreApellidoConsultor(confirmacion.getNombreApellidoConsultor());
            tramite.setFechaHoraVencimientoDocumentacion(confirmacion.getFechaHoraVencimientoDocumentacion());
        }
        
   
    return event.getNewStep();
    }
    
    
    
    public List<UIDatosCategoria> obtenerCategorias()
    {
         List<UIDatosCategoria> lc=new ArrayList<>();
         UIDatosCategoria dc;
         List<DTOCategoriaTramite> a=controladorSolicitarTramiteWeb.obtenerCategoriaTramite();
         for (DTOCategoriaTramite unC: a)
         {
         dc=new UIDatosCategoria();
         dc.setCodigo((unC.getCodCategoriaTramite()));
         dc.setNombre(unC.getNombreCategoriaTramite());
         lc.add(dc);
         }
         return lc;
                
    }
    public List<UITipoTramite> obtenerTramite() {
       
    
    List<UITipoTramite> tiposDeTramiteList = new ArrayList<>();
    List<DTOTipoTramiteWeb> tramites = controladorSolicitarTramiteWeb.elegirCategoriaTramite(codCategoriaTramite);
        System.out.println("Tramite");
    

    UITipoTramite dt;
    for (DTOTipoTramiteWeb tramite : tramites) {
        dt = new UITipoTramite();
        dt.setCodigoo(tramite.getCodTipoTramite());
        dt.setNombree(tramite.getNombreTipoTramite());
        dt.setDescripcionn(tramite.getDescripcionTipoTramiteWeb());
        tiposDeTramiteList.add(dt);
    }
        System.out.println("Cantidad"+tiposDeTramiteList);
     //   Messages.create("No se encontró ningún trámite para la categoría especificada." + codCategoriaTramite).detail("" + codCategoriaTramite).add();
    return tiposDeTramiteList;
}
   
     public void elegirCategorias(int codigo) {
         codCategoriaTramite=codigo;
         System.out.println("Categoria");
         PrimeFaces.current().executeScript("$('.ui-wizard-nav-next').click()");
                 //Messages.create("No se encontró ningún trámite para la categoría especificada."+codigo).detail(""+codigo).add();
                 //return "tramite";
               
       }  
       
     public void elegirTipoTramites(int codigoo ){
         codTipoTramite=codigoo;
         PrimeFaces.current().executeScript("$('.ui-wizard-nav-next').click()");
     }
     
    
  
    
         
        
    
     public String confirmarTramite() {
        // Lógica de confirmación
        PrimeFaces.current().executeScript("$('.ui-wizard-nav-next').click()");
       Messages.create("confirmacion Exitosa");
               return "confirmacionExitosa"; // Navegación a la página de confirmación exitosa
    }

    }
        
        
      
      
