package CompletarDocumentacion;

import CargarTramite.exceptions.TramiteException;
import CompletarDocumentacion.dtos.DTOArchivo;
import CompletarDocumentacion.dtos.DTOCompletarDoc;
import CompletarDocumentacion.dtos.DTODocumentacion;
import entidades.Tramite;
import entidades.TramiteDocumentacion;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

public class ExpertoCompletarDocumentacion {

    
    
    public DTOCompletarDoc seleccionarTramite(int nroTramite) throws TramiteException{
        
        if(nroTramite > 0){

            List<DTOCriterio> Criterio = new ArrayList<>();
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramite);
            Criterio.add(unCriterio);
            
            List<Object> objectList = FachadaPersistencia.getInstance().buscar("Tramite", Criterio);

            if(!objectList.isEmpty()){
                Tramite tramiteEncontrado = (Tramite)objectList.get(0);
                DTOCompletarDoc dtoCompletarDoc = new DTOCompletarDoc();
                dtoCompletarDoc.setNroTramite(tramiteEncontrado.getNroTramite());
                dtoCompletarDoc.setFechaVencimientoDocumentacion(tramiteEncontrado.getFechaHoraVencimientoDocumentacion());
                dtoCompletarDoc.setFechaHoraFinEntregaDoc(tramiteEncontrado.getFechaHoraFinEntregaDocumentacion());
                dtoCompletarDoc.setCuitCliente(tramiteEncontrado.getCliente().getCuitCliente());
                dtoCompletarDoc.setNombreApellidoCliente(tramiteEncontrado.getCliente().getNombreApellidoCliente());
                dtoCompletarDoc.setNombreTipoTramite(tramiteEncontrado.getTipoTramite().getNombreTipoTramite());

            for (TramiteDocumentacion tramiteDocumentacion : tramiteEncontrado.getTramiteDocumentacionList()) {
                DTODocumentacion dtoDocumentacion = new DTODocumentacion();
                dtoDocumentacion.setCodTramiteDocumentacion(tramiteDocumentacion.getCodTramiteDocumentacion());
                dtoDocumentacion.setNombreDocumentacion(tramiteDocumentacion.getDocumentacion().getNombreDocumentacion());
                dtoDocumentacion.setFechaHoraEntrega(tramiteDocumentacion.getFechaHoraEntrega());
                dtoCompletarDoc.addDtoDocumentacion(dtoDocumentacion);
            }
            return dtoCompletarDoc;
        
        }else{
            throw new TramiteException("No existe el trámite n°: " +nroTramite);
        }    
    }else{
            throw new TramiteException("Trámite n°: " +nroTramite +" incorrecto.");
        } 
    }
    
    
    
    public void guardarArchivo(int nroTramite, int codTramiteDocumentacion, DTOArchivo archivo) throws TramiteException{
        
        System.out.println("metodo guardar del experto");
        if(nroTramite > 0 && codTramiteDocumentacion > 0 && archivo != null){
            System.out.println(nroTramite);
            System.out.println(codTramiteDocumentacion);
            System.out.println(archivo.getNombreArchivo());
            System.out.println("los valores de los parametros llegan ok al metodo guardar");
        }else{
                    System.out.println(nroTramite);
            System.out.println(codTramiteDocumentacion);
            System.out.println(archivo.getNombreArchivo());
            System.out.println("los valores de los parametros fallan, metodo guardar");
        }
        
      try{
          FachadaPersistencia.getInstance().iniciarTransaccion();
      
        
            List<DTOCriterio> lCriterio = new ArrayList<>();

            DTOCriterio criterio = new DTOCriterio();
            criterio.setAtributo("nroTramite");
            criterio.setOperacion("=");
            criterio.setValor(nroTramite);
            lCriterio.add(criterio);

            List<Object> objectList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);

            if(!objectList.isEmpty()){

                Tramite tramite = (Tramite) objectList.get(0);
                
                List<TramiteDocumentacion> listaT = tramite.getTramiteDocumentacionList();
                int i=0;
                if(!listaT.isEmpty()){
                
                for(TramiteDocumentacion tramiteDoc : listaT){
                    
                    if(tramiteDoc.getCodTramiteDocumentacion() == codTramiteDocumentacion){
                        tramiteDoc.setNombreArchivo(archivo.getNombreArchivo());
                        tramiteDoc.setArchivo(archivo.getArchivo());
                            if(tramiteDoc.getArchivo() != null && tramiteDoc.getNombreArchivo() != null){
                                tramiteDoc.setFechaHoraEntrega(new Timestamp(System.currentTimeMillis()));
                                FachadaPersistencia.getInstance().guardar(tramiteDoc);
                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo guardado", "");
                                FacesContext.getCurrentInstance().addMessage(null, message);
                            }
                        }
                    
                    if(tramiteDoc.getFechaHoraEntrega() != null){
                        i++;
                    }
                    System.out.println("i = " +i);
                    
                    }if(i == tramite.getTramiteDocumentacionList().size()){
                        tramite.setFechaHoraFinEntregaDocumentacion(new Timestamp(System.currentTimeMillis()));
                        FachadaPersistencia.getInstance().guardar(tramite);
                    }
                }
                FachadaPersistencia.getInstance().finalizarTransaccion();
            }
        }catch(Exception e){
            throw new TramiteException("Error al intentar guardar el archivo");
        }
    }
    
    public DTOArchivo descargarArchivo (int nroTramite, int codTramiteDocumentacion) throws Exception{
        DTOArchivo archivoDescargado = new DTOArchivo();
     try{
          FachadaPersistencia.getInstance().iniciarTransaccion();
          
            List<DTOCriterio> lCriterio = new ArrayList<>();

            DTOCriterio criterio = new DTOCriterio();
            criterio.setAtributo("nroTramite");
            criterio.setOperacion("=");
            criterio.setValor(nroTramite);
            lCriterio.add(criterio);

            List<Object> objectList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);

            if(!objectList.isEmpty()){
                Tramite tramite = (Tramite) objectList.get(0);
                List<TramiteDocumentacion> listaT = tramite.getTramiteDocumentacionList();
                for(TramiteDocumentacion t : listaT){
                    if(t.getCodTramiteDocumentacion() == codTramiteDocumentacion){
                        if(t.getFechaHoraEntrega() != null){
                            archivoDescargado.setArchivo(t.getArchivo());
                            archivoDescargado.setNombreArchivo(t.getNombreArchivo());
                        }
                    }
                }
                FachadaPersistencia.getInstance().finalizarTransaccion();
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
     return archivoDescargado;
    }
    
    public void eliminarArchivo(int nroTramite, int codTramiteDocumentacion) throws TramiteException{
        if(nroTramite>0 && codTramiteDocumentacion>0){

            FachadaPersistencia.getInstance().iniciarTransaccion();
          
            List<DTOCriterio> lCriterio = new ArrayList<>();

            DTOCriterio criterio = new DTOCriterio();
            criterio.setAtributo("nroTramite");
            criterio.setOperacion("=");
            criterio.setValor(nroTramite);
            lCriterio.add(criterio);

            List<Object> objectList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
            
            if(!objectList.isEmpty()){
                    Tramite tramite = (Tramite)objectList.get(0);
                     for(TramiteDocumentacion td : tramite.getTramiteDocumentacionList()){
                         if(td.getCodTramiteDocumentacion() == codTramiteDocumentacion){
                             td.setArchivo(null);
                             td.setFechaHoraEntrega(null);
                             td.setNombreArchivo(null);
                             FachadaPersistencia.getInstance().iniciarTransaccion();
                         }else{
                             throw new TramiteException("No se encontro el codigo de documentacion para el tramite");
                    }
                }
            }else{throw new TramiteException("No se encontro el codigo de documentacion para el tramite");}
        }else{
            throw new TramiteException("No se pudo eliminar la documentacion");
        }
    }
}

