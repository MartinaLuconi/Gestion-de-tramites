/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;


import IniciarSesion.bean.UIIniciarSesion;
import RC_2.ControladorRC;
import RC_2.exceptionRC;
import RC_2.dto.DestinosPosiblesDTO;
import RC_2.dto.NuevoEstadoDTO;
import RC_2.dto.TramiteParticularDTO;
import RC_2.dto.TransicionDTO;
import RC_2.dto.datosConsultorDTO;
import com.github.adminfaces.template.session.AdminSession;
import entidades.TramiteEstado;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
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

@Named("uiNuevoEstado")
@ViewScoped
public class UInuevoEstado  implements Serializable{
    private List<String> nombreEstadoTramiteDestino;
    private List<EstadosPosiblesUI> estadosPosiblesUI=new ArrayList<>();
    private String nombreEstadoTramiteActual;
    private int codEstadoTramite;
    private String observacionTramiteEstado;
    private ControladorRC controladorRC = new ControladorRC();
    private int nroTramite;
    private String nombreTipoTramite;
    private String nombreApellidoCliente;
    private int codEstadoSeleccionado;
    private String nombreEstadoTramiteSeleccionado;
     //datos consultor
    private int legajoConsultor;
   @Inject
    private UIIniciarSesion session;

    public String getNombreEstadoTramiteSeleccionado() {
        return nombreEstadoTramiteSeleccionado;
    }

    public void setNombreEstadoTramiteSeleccionado(String nombreEstadoTramiteSeleccionado) {
        this.nombreEstadoTramiteSeleccionado = nombreEstadoTramiteSeleccionado;
    }

    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }
    
    

    public List<EstadosPosiblesUI> estadosPosibles() {
        return estadosPosiblesUI;
    }

    public int getCodEstadoSeleccionado() {
        return codEstadoSeleccionado;
    }

    public void setCodEstadoSeleccionado(int codEstadoSeleccionado) {
        this.codEstadoSeleccionado = codEstadoSeleccionado;
    }
    
    
    public UInuevoEstado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
         nroTramite = Integer.parseInt(request.getParameter("nroTramite"));
         try {
            //recupera consultor q inicio sesion
            Class.forName("jakarta.enterprise.inject.spi.CDI");
            AdminSession  adminS = CDI.current().select(AdminSession.class).get();
            session=(UIIniciarSesion) adminS;
            datosConsultorDTO datosConsultorDTO=controladorRC.obtenerConsultor(session.getUserCod());
            legajoConsultor= datosConsultorDTO.getLegajoConsultor();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UITramiteParticular.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
         NuevoEstadoDTO nuevoEstadoDTO;
        
            nuevoEstadoDTO = controladorRC.nuevoEstado(legajoConsultor,nroTramite);
       
            nombreEstadoTramiteActual=nuevoEstadoDTO.getNombreEstadoTramiteActual();
            observacionTramiteEstado=nuevoEstadoDTO.getObservacionTramiteEstado();
            nombreApellidoCliente=nuevoEstadoDTO.getNombreApellidoCliente();
            nombreTipoTramite=nuevoEstadoDTO.getNombreTipoTramite();
            nroTramite=nuevoEstadoDTO.getNroTramite();
            System.out.println(nuevoEstadoDTO.getDestinosPosiblesList());
            for (DestinosPosiblesDTO d : nuevoEstadoDTO.getDestinosPosiblesList())
               {
                  EstadosPosiblesUI e = new EstadosPosiblesUI();
                  e.setCodEstadoTramite(d.getCodEstadoTramite());
                  e.setNombreEstadoTramiteDestino(d.getNombreEstadoTramiteDestino());
                  estadosPosiblesUI.add(e);

              }
         
        } catch (exceptionRC ex) {
             Logger.getLogger(UITramiteParticular.class.getName()).log(Level.SEVERE, null, ex);
             Messages.create(ex.getMessage()).error().add();
        }
        
         
         
//                  EstadosPosiblesUI e = new EstadosPosiblesUI();
//         e.setCodEstadoTramite(1);
//         e.setNombreEstadoTramiteDestino("Cristian");
//         estadosPosiblesUI.add(e);
//                           e = new EstadosPosiblesUI();
//         e.setCodEstadoTramite(2);
//         e.setNombreEstadoTramiteDestino("Martina");
//         estadosPosiblesUI.add(e);
//                            e = new EstadosPosiblesUI();
//        e.setCodEstadoTramite(3);
//        e.setNombreEstadoTramiteDestino("Etapa 1");
//        estadosPosiblesUI.add(e);
        
    }

   
    public String getNombreEstadoTramiteActual() {
        return nombreEstadoTramiteActual;
    }

    public void setNombreEstadoTramiteActual(String nombreEstadoTramiteActual) {
        this.nombreEstadoTramiteActual = nombreEstadoTramiteActual;
    }

    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }

    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    public String getObservacionTramiteEstado() {
        return observacionTramiteEstado;
    }

    public void setObservacionTramiteEstado(String observacionTramiteEstado) {
        this.observacionTramiteEstado = observacionTramiteEstado;
    }
    
 public String irCancelar(){
         BeansUtils.guardarUrlAnterior();
         return "tramiteParticular.xhtml?faces-redirect=true&nroTramite=" + nroTramite;
    }
    
    
    public String cambiarEstadoTramite() throws exceptionRC {
    try {
        // Verificamos si el codEstadoSeleccionado es nulo o vacío
        if (this.codEstadoSeleccionado == 0) {
            System.out.println("Error: El código de estado seleccionado es nulo o vacío.");
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El código de estado seleccionado es obligatorio", ""));
            return null;  // Devolvemos null para evitar continuar con la ejecución si hay un error
        }

        // Creamos el objeto TransicionDTO y seteamos los valores
        TransicionDTO transicionDTO = new TransicionDTO();
        transicionDTO.setCodEstadoTramite(getCodEstadoSeleccionado());
        transicionDTO.setNombreEstadoTramite(getNombreEstadoTramiteSeleccionado());
        transicionDTO.setObservacion(getObservacionTramiteEstado());

        // Llamamos al controlador para cambiar el estado del trámite
        controladorRC.cambiarEstadoTramite(transicionDTO, nroTramite);

        // Redirigimos a la página anterior
        return BeansUtils.redirectToPreviousPage();

    } catch (Exception e) {
        // Capturamos cualquier excepción que pueda ocurrir
        System.out.println("Error al cambiar el estado del trámite: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cambiar el estado del trámite", e.getMessage()));

        // Devolvemos null para evitar la continuación del flujo en caso de error
        return null;
    }
}
    
    

    
    
    
}
