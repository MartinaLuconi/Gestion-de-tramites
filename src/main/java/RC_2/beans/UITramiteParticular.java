/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;

import IniciarSesion.bean.UIIniciarSesion;
import RC_2.ControladorRC;
import RC_2.dto.HistoricoDTO;
import RC_2.dto.TramiteParticularDTO;
import RC_2.dto.TramitesConsultorDTO;
import RC_2.dto.datosConsultorDTO;
import RC_2.exceptionRC;
import com.github.adminfaces.template.session.AdminSession;
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

@Named("uiTramiteParticular")
@ViewScoped
public class UITramiteParticular implements Serializable {
   //PARA MOSTRAR DATOS
    //det dto tramite particular
    private int codTipoTramite;
    private Timestamp fechaHoraFinEntregaDoc;
    private String nombreApellidoCliente;
    private float precioTipoTramite;
    //del dto consultor
    private String cuitCliente;
    private Timestamp fechaHoraCargaTramite;
    private Timestamp fechaHoraFinTramite;
    private String nombreEstadoTramite;
    private String nombreTipoTramite;
    private int nroTramite;
    private String observacionTramiteEstado;  
    List<UIHistorico>uiHistoricoList=new ArrayList<>();
    //datos consultor
    private int legajoConsultor;
   @Inject
    private UIIniciarSesion session;
    
    
    private ControladorRC controladorRegistrarCambioEstado = new ControladorRC();

    
    public UITramiteParticular(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        nroTramite = Integer.parseInt(request.getParameter("nroTramite"));
        System.out.println(nroTramite);
        try {
            //recupera consultor q inicio sesion
            Class.forName("jakarta.enterprise.inject.spi.CDI");
            AdminSession  adminS = CDI.current().select(AdminSession.class).get();
            session=(UIIniciarSesion) adminS;
            datosConsultorDTO datosConsultorDTO=controladorRegistrarCambioEstado.obtenerConsultor(session.getUserCod());
            legajoConsultor= datosConsultorDTO.getLegajoConsultor();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UITramiteParticular.class.getName()).log(Level.SEVERE, null, ex);
        }
   
         
         
        TramiteParticularDTO tramiteParticularDTO;
        try {
        tramiteParticularDTO = controladorRegistrarCambioEstado.obtenerTramiteParticular(legajoConsultor,nroTramite);
        
        nombreApellidoCliente=tramiteParticularDTO.getNombreApellidoCliente();
        codTipoTramite=tramiteParticularDTO.getCodTipoTramite();
        fechaHoraFinEntregaDoc=tramiteParticularDTO.getFechaHoraFinEntregaDoc();
        precioTipoTramite=tramiteParticularDTO.getPrecioTipoTramite();
        observacionTramiteEstado=tramiteParticularDTO.getObservaciones();
        fechaHoraFinTramite=tramiteParticularDTO.getFechaHoraFinTramite();
       
       TramitesConsultorDTO tramitesConsultorDTO = controladorRegistrarCambioEstado.buscarTramiteConsultor(nroTramite);
       cuitCliente = tramitesConsultorDTO.getCuitCliente();
       fechaHoraCargaTramite = tramitesConsultorDTO.getFechaHoraCargaTramite();
       nombreEstadoTramite = tramitesConsultorDTO.getNombreEstadoTramite();
       nombreTipoTramite = tramitesConsultorDTO.getNombreTipoTramite();
         
      if(fechaHoraFinTramite!=null){
          //Messages.create("El trámite ya finalizó").detail("Finalizado").add();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Trámite finalizado", "El trámite ha sido finalizado correctamente."));

      }
      
//      if(fechaHoraBajaTramite!=null){
//          Messages.create("El trámite ya finalizó").detail("Finalizado").add();
//      }
        
        List<HistoricoDTO> historicosDTO = tramiteParticularDTO.getHistoricoDTOList();
        for (HistoricoDTO HistoricoDTO : historicosDTO) {
            UIHistorico uiHistoricoDTO = new UIHistorico();
            uiHistoricoDTO.setCodEstadoTramite(HistoricoDTO.getCodEstadoTramite());
            uiHistoricoDTO.setFechaCambio(HistoricoDTO.getFechaHastaTramiteEstado());
            uiHistoricoDTO.setFechaBajaTramiteEstado(HistoricoDTO.getFechaHoraBajaTramiteEstado());
            uiHistoricoDTO.setNombreEstadoTramite(HistoricoDTO.getNombreEstadoTramite());
            uiHistoricoDTO.setNroLineaTramiteEstado(HistoricoDTO.getNroLineaTramiteEstado());
            uiHistoricoDTO.setObservacionTramiteEstado(HistoricoDTO.getObservaciones());
            uiHistoricoList.add(uiHistoricoDTO);
        }
       
       } catch (exceptionRC ex) {
            Logger.getLogger(UITramiteParticular.class.getName()).log(Level.SEVERE, null, ex);
             Messages.create(ex.getMessage()).error().add();
        }
       

    }

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    public Timestamp getFechaHoraFinTramite() {
        return fechaHoraFinTramite;
    }

    public void setFechaHoraFinTramite(Timestamp fechaHoraFinTramite) {
        this.fechaHoraFinTramite = fechaHoraFinTramite;
    }
    
    
    public Timestamp getFechaHoraFinEntregaDoc() {
        return fechaHoraFinEntregaDoc;
    }

    public void setFechaHoraFinEntregaDoc(Timestamp fechaHoraFinEntregaDoc) {
        this.fechaHoraFinEntregaDoc = fechaHoraFinEntregaDoc;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public float getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    public void setPrecioTipoTramite(float precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }

    public String getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public Timestamp getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }

    public void setFechaHoraCargaTramite(Timestamp fechaHoraCargaTramite) {
        this.fechaHoraCargaTramite = fechaHoraCargaTramite;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public String getObservacionTramiteEstado() {
        return observacionTramiteEstado;
    }

    public void setObservacionTramiteEstado(String observacionTramiteEstado) {
        this.observacionTramiteEstado = observacionTramiteEstado;
    }

    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }
    

  
    
    public void anularUltimo(int nroTramite) {
        try{
            controladorRegistrarCambioEstado.anularUltimo(nroTramite);
            Messages.create("Anulado").detail("Anulado").add();
        }catch(exceptionRC ex){
           Logger.getLogger(UInuevoEstado.class.getName()).log(Level.SEVERE, null, ex);
           Messages.create(ex.getMessage()).error().add();
        }
    }
    
    public String irNuevoEstado() {
        BeansUtils.guardarUrlAnterior();
        return "transicion.xhtml?faces-redirect=true&nroTramite=" + nroTramite; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
    public String irPrueba() {
        BeansUtils.guardarUrlAnterior();
        return "casoPrueba.xhtml?faces-redirect=true&nroTramite=" + nroTramite; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
    
//    public String irEtapas(int nro){
//         BeansUtils.guardarUrlAnterior();
//         return "tramiteParticular.xhtml?faces-redirect=true&codigo=" + nro;
//    }
    
    public List<UIHistorico> etapas(int nroTramite){
                return uiHistoricoList;
     }
    
}
