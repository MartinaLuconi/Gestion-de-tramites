/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;


import RC_2.ControladorRC;
import RC_2.exceptionRC;
import RC_2.dto.DestinosPosiblesDTO;
import RC_2.dto.NuevoEstadoDTO;
import RC_2.dto.TramiteParticularDTO;
import RC_2.dto.TransicionDTO;
import entidades.TramiteEstado;
import jakarta.faces.application.FacesMessage;
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

@Named("uiNuevoEstadoCP")
@ViewScoped
public class UInuevoEstadoCP  implements Serializable{
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
    
    
    public UInuevoEstadoCP() throws exceptionRC {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
         nroTramite = Integer.parseInt(request.getParameter("nroTramite"));
         NuevoEstadoDTO nuevoEstadoDTO=controladorRC.nuevoEstadoCP(nroTramite);
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
         
         //nombreEstadoTramiteDestino.add(e.getNombreEstadoTramiteDestino());
         
        }
        
        
         
         
//         EstadosPosiblesUI e = new EstadosPosiblesUI();
//         e.setCodEstadoTramite(1);
//         e.setNombreEstadoTramiteDestino("CasoPrueba");
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
    
    public String cambiarEstadoTramite() {
    try {
        // Obtener los valores de los atributos
        int codEstadoSeleccionado = getCodEstadoSeleccionado();
        String nombreEstadoSeleccionado = getNombreEstadoTramiteSeleccionado();

        // Validar si el código o nombre de estado están vacíos
        if (codEstadoSeleccionado == 0) {
            // Lanzar un error personalizado si alguno de los campos está vacío
            throw new IllegalArgumentException("El Estado seleccionado es incorrecto. Por favor, seleccione un estado válido.");
        }

        // Crear el DTO con los valores proporcionados
        TransicionDTO transicionDTO = new TransicionDTO();
        transicionDTO.setCodEstadoTramite(codEstadoSeleccionado);
        transicionDTO.setNombreEstadoTramite(nombreEstadoSeleccionado);
        transicionDTO.setObservacion(getObservacionTramiteEstado());

        // Intentar cambiar el estado del trámite
        controladorRC.cambiarEstadoTramite(transicionDTO, nroTramite);

    } catch (IllegalArgumentException e) {
        // Capturamos la excepción personalizada y mostramos el mensaje de error
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        return null; // No redirigir a la nueva página, permanecemos en la misma página
    } catch (Exception e) {
        // Capturamos cualquier otra excepción general y mostramos un mensaje de error
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado: El Estado seleccionado es incorrecto. Por favor, seleccione un estado válido", ""));
        return null; // No redirigir a la nueva página
    }

    // Si todo está bien, redirigir a la página anterior
    return BeansUtils.redirectToPreviousPage();
}

    
    

    
    
    
}
