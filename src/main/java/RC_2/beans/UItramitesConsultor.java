/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;

import IniciarSesion.bean.UIIniciarSesion;
import RC_2.ControladorRC;
import RC_2.dto.TramitesConsultorDTO;
import RC_2.dto.datosConsultorDTO;
import RC_2.exceptionRC;
import com.github.adminfaces.template.config.AdminConfig;
import com.github.adminfaces.template.session.AdminSession;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

@Named("uiTramitesConsultor")
@ViewScoped
public class UItramitesConsultor implements Serializable{

    //filtros
    private int nroTramiteFiltro=0; //no se usa, no sirve
    private String nombreTipoTramiteFiltro=" ";
    private String estadoFiltro=" ";
    private Date fechaFiltro;
    private int legajoConsultor;
    private String nombreConsultor;
    private ControladorRC controladorRegistrarCambioEstado = new ControladorRC();
    @Inject
    private UIIniciarSesion session;
    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }

    public String getNombreConsultor() {
        return nombreConsultor;
    }

    public void setNombreConsultor(String nombreConsultor) {
        this.nombreConsultor = nombreConsultor;
    }



    public int getNroTramiteFiltro() {
        return nroTramiteFiltro;
    }

    public void setNroTramiteFiltro(int nroTramiteFiltro) {
        this.nroTramiteFiltro = nroTramiteFiltro;
    }

    public String getNombreTipoTramiteFiltro() {
        return nombreTipoTramiteFiltro;
    }

    public void setNombreTipoTramiteFiltro(String nombreTipoTramiteFiltro) {
        this.nombreTipoTramiteFiltro = nombreTipoTramiteFiltro;
    }

    public String getEstadoFiltro() {
        return estadoFiltro;
    }

    public void setEstadoFiltro(String estadoFiltro) {
        this.estadoFiltro = estadoFiltro;
    }

    public Date getFechaFiltro() {
        return fechaFiltro;
    }

    public void setFechaFiltro(Date fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }

    public UItramitesConsultor() {
        try {
            Class.forName("jakarta.enterprise.inject.spi.CDI");
   
          AdminSession  adminS = CDI.current().select(AdminSession.class).get();
         session=(UIIniciarSesion) adminS;
         datosConsultorDTO datosConsultorDTO=controladorRegistrarCambioEstado.obtenerConsultor(session.getUserCod());
            legajoConsultor= datosConsultorDTO.getLegajoConsultor();
            nombreConsultor= datosConsultorDTO.getNombreConsultor();
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UItramitesConsultor.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(ex.getMessage()).error().add();
        }
        
//        List<TramitesConsultorDTO> tramitesConsultorDTO;
//        try {     
//                tramitesConsultorDTO = controladorRegistrarCambioEstado.obtenerTramitesConsultor(session.getUserCod(),fechaFiltro, nombreTipoTramiteFiltro, estadoFiltro);
//           
//
//                
//        } catch (exceptionRC ex) {
//                Logger.getLogger(UItramitesConsultor.class.getName()).log(Level.SEVERE, null, ex);
//                Messages.create(ex.getMessage()).error().add();
//            }
    }
    
    
    public List<UITramitesConsultorGrilla> obtenerTramitesConsultor(String usuario ){
   
        List<UITramitesConsultorGrilla> tramitesGrilla = new ArrayList<>();
       
        List<TramitesConsultorDTO> tramitesConsultorDTO;
        try {
        
        tramitesConsultorDTO = controladorRegistrarCambioEstado.obtenerTramitesConsultor(session.getUserCod(),fechaFiltro, nombreTipoTramiteFiltro, estadoFiltro);
//                TramitesConsultorDTO dtoc = tramitesConsultorDTO.get(0);
//                legajoConsultor= dtoc.getConsultorAsignado().getLegajoConsultor();
//                nombreConsultor=dtoc.getConsultorAsignado().getNombreConsultor();
        for (TramitesConsultorDTO tramiteConsultorDTO : tramitesConsultorDTO) {
            UITramitesConsultorGrilla tramiteGrillaUI = new UITramitesConsultorGrilla();
            
            tramiteGrillaUI.setNroTramite(tramiteConsultorDTO.getNroTramite());
            tramiteGrillaUI.setNombreTipoTramite(tramiteConsultorDTO.getNombreTipoTramite());
            tramiteGrillaUI.setFechaInicio(tramiteConsultorDTO.getFechaHoraCargaTramite());
            tramiteGrillaUI.setCuitCliente(tramiteConsultorDTO.getCuitCliente());
            tramiteGrillaUI.setEstado(tramiteConsultorDTO.getNombreEstadoTramite());
            
            tramitesGrilla.add(tramiteGrillaUI);
           
        }
        tramitesGrilla.sort(Comparator.comparing(UITramitesConsultorGrilla::getNroTramite).reversed());

        
        return tramitesGrilla;
        } catch (exceptionRC ex) {
            Logger.getLogger(UItramitesConsultor.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(ex.getMessage()).error().add();
            return tramitesGrilla;
        }
    }
    
    public void filtrar(){
    }
    
  
    
    public String irATramite(int nro) {
        BeansUtils.guardarUrlAnterior();
        return "tramiteParticular.xhtml?faces-redirect=true&nroTramite=" + nro; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
    
    
    public String irATramitesInactivos() {
        BeansUtils.guardarUrlAnterior();
        return "tramitesConsultorInactivos.xhtml?faces-redirect=true&"; // Usa '?faces-redirect=true' para hacer una redirección
    }    
    
}
