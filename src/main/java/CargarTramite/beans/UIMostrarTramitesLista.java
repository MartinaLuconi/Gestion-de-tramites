package CargarTramite.beans;
import CargarTramite.ControladorCargarTramite;
import CargarTramite.dtos.DTOTramiteV;
import CargarTramite.exceptions.TramiteException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.omnifaces.util.Messages;

@Named("uimostrarTramitesLista")
@ViewScoped
public class UIMostrarTramitesLista implements Serializable {

    private ControladorCargarTramite controladorCargarTramite = new ControladorCargarTramite();
    private Date fechaDesdeFiltro=null;
    private String cuitFiltro="";
    private int nroTramiteFiltro=0;
    private String estadoFiltro="";
    private int consultorFiltro=0;
    private int codigoTTFiltro=0;

    public UIMostrarTramitesLista(){}


    public void filtrar() {
    }
 

    public List<TramiteGrillaUI> mostrarTramites() {
        List<TramiteGrillaUI> tramitesGrilla = new ArrayList<>();

        List<DTOTramiteV> tramitesDTO = controladorCargarTramite.mostrarTramites(fechaDesdeFiltro, cuitFiltro,
                nroTramiteFiltro, estadoFiltro, consultorFiltro, codigoTTFiltro);

        for (DTOTramiteV tramiteDTO : tramitesDTO) {
            TramiteGrillaUI tramiteGrillaUI = new TramiteGrillaUI();
            tramiteGrillaUI.setNroTramite(tramiteDTO.getNroTramite());
            tramiteGrillaUI.setCUITCliente(tramiteDTO.getCuitCliente());
            tramiteGrillaUI.setNombreCliente(tramiteDTO.getNombreCliente());
            tramiteGrillaUI.setNombreTipoTramite(tramiteDTO.getNombreTipoTramite());
            tramiteGrillaUI.setNombreConsultor(tramiteDTO.getNombreConsultor());
            tramiteGrillaUI.setNombreEstadoTramite(tramiteDTO.getNombreEstadoTramite());

            // Convertimos fechaHoraCargaTramite a fechaCargaTramite
            Timestamp fechaTime = tramiteDTO.getFechaHoraCargaTramite();
            Date fechaCarga = new Date(fechaTime.getTime());
            tramiteGrillaUI.setFechaCargaTramite(fechaCarga);

            // Convertimos fechaHoraBajaTramite a fechaHoraBajaTramite, si es que existe
            Timestamp fechaTime2 = tramiteDTO.getFechaHoraBajaTramite();
            if (fechaTime2 != null) {
                Date fechaBaja = new Date(fechaTime2.getTime());
                tramiteGrillaUI.setFechaHoraBajaTramite(fechaBaja);
            }

            // Convertimos fechaHoraFinTramite a fechaHoraFinTramite, si es que existe
            Timestamp fechaTime3 = tramiteDTO.getFechaHoraFinTramite();
            if (fechaTime3 != null) {
                Date fechaFin = new Date(fechaTime3.getTime());
                tramiteGrillaUI.setFechaHoraFinTramite(fechaFin);
            }

            // Determinamos si la documentación está completa o no
            Timestamp fechaFinDoc = tramiteDTO.getFechaHoraFinEntregaDoc();
            if (fechaFinDoc == null) {
                tramiteGrillaUI.setEstadoDocumentacion("Incompleta");
            } else {
                tramiteGrillaUI.setEstadoDocumentacion("Completa");
            }

            tramitesGrilla.add(tramiteGrillaUI);
        }

        // Ordenar la lista en orden descendente por el número de trámite
        tramitesGrilla.sort(Comparator.comparing(TramiteGrillaUI::getNroTramite).reversed());

        return tramitesGrilla;
    }



    public String irBotonCargar() throws IOException{
        System.out.println("boton cargar");
        return "cargarTramite?faces-redirect=true";
    }
    
    
    public void anularTramite(int nroTramite) throws TramiteException {
    try {
        controladorCargarTramite.anularTramite(nroTramite);
        Messages.create("Éxito").detail("El trámite n°: " +nroTramite +" ha sido anulado exitosamente.").add();

    } catch (TramiteException e) {
        Messages.create("Error").error().detail(e.getMessage()).add();
    } catch (Exception e) {
        Messages.create("Error inesperado").error().detail("Ocurrió un error al intentar anular el trámite n°: " +nroTramite).add();
    }
    
   }
    
    
    public String irVisualizarTramite(int nroTramite)throws IOException{
       return "visualizarTramite?faces-redirect=true&nroTramite="+nroTramite;
    }
    
    
//    //Getters and Setters
    public ControladorCargarTramite getControladorCargarTramite() {
        return controladorCargarTramite;
    }

    public Date getFechaDesdeFiltro() {
        return fechaDesdeFiltro;
    }

    public void setFechaDesdeFiltro(Date fechaDesdeFiltro) {
        this.fechaDesdeFiltro = fechaDesdeFiltro;
    }

    public String getCuitFiltro() {
        return cuitFiltro;
    }

    public void setCuitFiltro(String cuitFiltro) {
        this.cuitFiltro = cuitFiltro;
    }

    public int getNroTramiteFiltro() {
        return nroTramiteFiltro;
    }

    public void setNroTramiteFiltro(int nroTramiteFiltro) {
        this.nroTramiteFiltro = nroTramiteFiltro;
    }

    public String getEstadoFiltro() {
        return estadoFiltro;
    }

    public void setEstadoFiltro(String estadoFiltro) {
        this.estadoFiltro = estadoFiltro;
    }
    
    public int getConsultorFiltro() {
        return consultorFiltro;
    }

    public void setConsultorFiltro(int consultorFiltro) {
        this.consultorFiltro = consultorFiltro;
    }

    public int getCodigoTTFiltro() {
        return codigoTTFiltro;
    }

    public void setCodigoTTFiltro(int codigoTTFiltro) {
        this.codigoTTFiltro = codigoTTFiltro;
    }


}
