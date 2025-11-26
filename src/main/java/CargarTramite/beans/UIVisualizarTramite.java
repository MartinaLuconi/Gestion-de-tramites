
package CargarTramite.beans;

import CargarTramite.ControladorCargarTramite;
import CargarTramite.dtos.DTODocumentacion;
import CargarTramite.dtos.DTOVisualizarTramite;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named("uivisualizarTramite")
@ViewScoped
public class UIVisualizarTramite implements Serializable{


    private ControladorCargarTramite controladorCargarTramite = new ControladorCargarTramite();
    private VisualizarTramiteGrilla grilla = new VisualizarTramiteGrilla();
    private int nroTramite; //atributo que almancena el valor del parametro
    
    

        public VisualizarTramiteGrilla visualizarTramite()throws Exception{
            
            System.out.println("VISUALIZAR TRAMITE");
            
            String nroRecuperado = FacesContext.getCurrentInstance()
                    .getExternalContext().getRequestParameterMap().get("nroTramite");
            
            System.out.println("numero recuperado" +nroRecuperado);
            
             if (nroRecuperado != null){ 
                try {
                    int nroTramite = Integer.parseInt(nroRecuperado);
                    DTOVisualizarTramite dto = controladorCargarTramite.visualizarTramite(nroTramite);
           
                    grilla.setNroTramite(dto.getNroTramite());
                    grilla.setFechaHoraCargaTramite(dto.getFechaHoraCargaTramite());
                    grilla.setNombreTipoTramite(dto.getNombreTipoTramite());
                    grilla.setNombreCliente(dto.getNombreCliente());
                    grilla.setCuitCliente(dto.getCuitCliente());
                    grilla.setDireccion(dto.getDireccion());
                    grilla.setNombreApellidoConsultor(dto.getNombreApellidoConsultor());
                    grilla.setNombreEstadoTramite(dto.getNombreEstadoTramite());
                    grilla.setPrecioTramite(dto.getPrecioTramite());

                       List<DTODocumentacion> docRecu = dto.getDtoDocumentaciones();
                        for(DTODocumentacion d : docRecu){
                            DTODocumentacion docDTO = new DTODocumentacion();
                            docDTO.setNombreDocumentacion(d.getNombreDocumentacion());
                            if(d.getFechaHoraEntrega() != null){
                            docDTO.setFechaHoraEntrega(d.getFechaHoraEntrega());
                            }
                             System.out.println("fecha de entrega: " + docDTO.getFechaHoraEntrega());
                             
                             grilla.addDocumentacion(docDTO);
                        }
                        
                    grilla.setFechaHoraFinEntregaDoc(dto.getFechaHoraFinEntregaDoc());
                    
                    //para anular boton completar documentacion
                    grilla.setFechaHoraBajaTramite(dto.getFechaHoraBajaTramite());
                    grilla.setFechaHoraFinTramite(dto.getFechaHoraFinTramite());
                   
                    return grilla;
                    
                    } catch (Exception e) {
                        // Manejo de errores si el número de trámite no es válido
                        throw new Exception("Error, número de trámite inválido.",e);
                    }
                }
             return null;
            }

    public String irACompletarDocumentacion(int nroTramite) throws IOException{
        return "/CompletarDocumentacion/completarDocumentacion?faces-redirect=true&nroTramite="+nroTramite;
    }
   

    //getters and setters
    public ControladorCargarTramite getControladorCargarTramite() {
        return controladorCargarTramite;
    }

    public void setControladorCargarTramite(ControladorCargarTramite controladorCargarTramite) {
        this.controladorCargarTramite = controladorCargarTramite;
    }
    
    
    public VisualizarTramiteGrilla getGrilla() {
        return grilla;
    }

    public void setGrilla(VisualizarTramiteGrilla grilla) {
        this.grilla = grilla;
    }

    
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }
    
}