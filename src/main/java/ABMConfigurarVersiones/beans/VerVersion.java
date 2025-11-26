/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.beans;

import ABMConfigurarVersiones.dtos.DTOEstadoActualInput;
import ABMConfigurarVersiones.dtos.DTOEstadoDestinoInput;
import ABMConfigurarVersiones.dtos.DTOEstadoPosible;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
import ABMConfigurarVersiones.dtos.DTOVersionyEstados;
import ABMConfigurarVersiones.exceptions.ControladorABMVersion;
import ABMConfigurarVersiones.exceptions.VersionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

@Named("uiVer")
@ViewScoped
 
public class VerVersion implements Serializable{
    
    private ControladorABMVersion controlador = new ControladorABMVersion();
    private String cargarJSON = "";
    private String titulo = "";
    private boolean editable;
    private boolean insert;
    private int nroVersion;
    private int codigoTipoTramite;
    private Timestamp fechaHoraDesdeVersion;
    private Timestamp fechaHoraHastaVersion;
    private String nodosPosibles = "";

    public String getNodosPosibles() {
        return nodosPosibles;
    }

    public void setNodosPosibles(String nodosPosibles) {
        this.nodosPosibles = nodosPosibles;
    }
    
    
    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public int getCodigoTipoTramite() {
        return codigoTipoTramite;
    }

    public void setCodigoTipoTramite(int codigoTipoTramite) {
        this.codigoTipoTramite = codigoTipoTramite;
    }

    public Timestamp getFechaHoraDesdeVersion() {
        return fechaHoraDesdeVersion;
    }

    public void setFechaHoraDesdeVersion(Timestamp fechaHoraDesdeVersion) {
        this.fechaHoraDesdeVersion = fechaHoraDesdeVersion;
    }

    public Timestamp getFechaHoraHastaVersion() {
        return fechaHoraHastaVersion;
    }

    public void setFechaHoraHastaVersion(Timestamp fechaHoraHastaVersion) {
        this.fechaHoraHastaVersion = fechaHoraHastaVersion;
    }
    
    public void setNroVersion(int nroVersion) {
        this.nroVersion = nroVersion;
    }

    public int getNroVersion() {
        return nroVersion;
    }
   

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public VerVersion() throws VersionException  {
        //Diagrama
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int codigo = Integer.parseInt(request.getParameter("codigoTipoTramite"));
        codigoTipoTramite=codigo;
        boolean editaOve = Boolean.parseBoolean(request.getParameter("editable"));
        editable = editaOve;
        int codver = Integer.parseInt(request.getParameter("codVersion"));
        nroVersion = codver;
        
        DTOVersionOutput versionoutput =null;
        
        insert=false;
        try {
           versionoutput = controlador.verVersion(codigo, codver);
        } catch (VersionException ex) {
            Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);   
        }
        setNroVersion(versionoutput.getCodVersion());
        setFechaHoraDesdeVersion(versionoutput.getFechaHoraDesdeVersion());
        setFechaHoraHastaVersion(versionoutput.getFechaHoraHastaVersion());
             
                   
          List<NodoMenuIU> lestadosP = new ArrayList<NodoMenuIU>();
        
            List<DTOEstadoPosible> estados = new ArrayList();
                 NodoMenuIU unEP = new NodoMenuIU();
                 unEP.setCodigo(1);
                 unEP.setNombre("Iniciado");
                 lestadosP.add(unEP);
               
            Gson gson = new Gson();
            nodosPosibles = gson.toJson(lestadosP);
            
            List<NodoIU> lestados = new ArrayList<NodoIU>();
        
            if(versionoutput==null || versionoutput.getDraw().trim().length()==0)
            {
                //VersionException ex =  new VersionException("No hay version para visualizar");
               // Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);
                List<Integer> a = new ArrayList();
                        NodoIU unE = new NodoIU();
                        unE.setCodigo(1);
                        unE.setNombre("Iniciado");
                        unE.setXpos(80);
                        unE.setYpos(80);
                        lestados.add(unE);  
                    
                }  
           
            else
            {
                cargarJSON=versionoutput.getDraw();
            }       
       titulo = "Versión";

    }

    public String getCargarJSON() {
        return cargarJSON;
    }

    public void setCargarJSON(String cargarJSON) {
        this.cargarJSON = cargarJSON;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String volver(){
            return BeansUtils.redirectToPreviousPage();
    }
    
      public  String irHistorialVersion(int codigo) {
        //List<DTOVersionHistorial> listResultado = controlador.historialVersiones(codigo);
        //BeansUtils.guardarUrlAnterior();
        return "/ABMConfigurarVersiones/HistorialVersiones.xhtml?tipoTramite="+ codigo; // Usa '?faces-redirect=true' para hacer una redirección
      }
//     
//     public String irFinalizar() {
//         return "/ABMConfigurarVersiones/VersionGrillaUI.xhtml?faces-redirect=true";
//              }
}
     
     
