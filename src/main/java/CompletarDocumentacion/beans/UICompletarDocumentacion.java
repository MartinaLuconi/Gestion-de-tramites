package CompletarDocumentacion.beans;

import CompletarDocumentacion.ControladorCompletarDocumentacion;
import CompletarDocumentacion.dtos.DTOArchivo;
import CompletarDocumentacion.dtos.DTOCompletarDoc;
import CompletarDocumentacion.dtos.DTODocumentacion; 
import CargarTramite.exceptions.TramiteException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

@Named("uicompletarDocumentacion")
@ViewScoped
public class UICompletarDocumentacion implements Serializable {

    
    private ControladorCompletarDocumentacion controladorCompletarDocumentacion = new ControladorCompletarDocumentacion();

    private int nroTramite;
    private DTOCompletarDoc dtoCompletarDoc;
    private List<DocumentacionIU> documentos = new ArrayList<>();
    private DTOArchivo archivoDTO = new DTOArchivo();
    private int nroTramiteExtends;
    private boolean banderaCarga = false;
    private boolean banderaDescarga = false;

    
    
    @PostConstruct
    public void init() throws TramiteException {
     // Lógica para inicializar tus variables
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        // Recuperar los parámetros de la URL
        String nroTramiteParam = externalContext.getRequestParameterMap().get("nroTramite");

        // Asignar los valores a las variables de instancia
        if (nroTramiteParam != null) {
            this.nroTramiteExtends = Integer.parseInt(nroTramiteParam);
            this.banderaCarga = true;
            this.banderaDescarga = true;
        }
            seleccionarTramite(this.nroTramiteExtends);
    }
    
    

    public void seleccionarTramite(int nroTramite) throws TramiteException {
        
    try{
            dtoCompletarDoc = controladorCompletarDocumentacion.seleccionarTramite(nroTramite);
            if (dtoCompletarDoc != null) {
                documentos.clear();
                for (DTODocumentacion d : dtoCompletarDoc.getDtoDocumentacion()) {
                    DocumentacionIU doc = new DocumentacionIU();
                    doc.setNroTramite(nroTramite);
                    doc.setCodTramiteDocumentacion(d.getCodTramiteDocumentacion());
                    doc.setFechaHoraEntrega(d.getFechaHoraEntrega());
                    doc.setNombreDocumentacion(d.getNombreDocumentacion());
                    documentos.add(doc);
                }
            }
    }catch(TramiteException e) {
        Messages.create("Error").error().detail(e.getMessage()).add();
        }
    }
    
    
    
    public void seleccionarTramiteExtends(int nroTramiteExtends) throws TramiteException {
    try{
        dtoCompletarDoc = controladorCompletarDocumentacion.seleccionarTramite(nroTramiteExtends);
        if (dtoCompletarDoc != null) {
            documentos.clear();
            for (DTODocumentacion d : dtoCompletarDoc.getDtoDocumentacion()) {
                DocumentacionIU doc = new DocumentacionIU();
                doc.setNroTramite(nroTramite);
                doc.setCodTramiteDocumentacion(d.getCodTramiteDocumentacion());
                doc.setFechaHoraEntrega(d.getFechaHoraEntrega());
                doc.setNombreDocumentacion(d.getNombreDocumentacion());
                documentos.add(doc);
            }
        }
    }catch(TramiteException e) {
        Messages.create("Error").error().detail(e.getMessage()).add();
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) throws Exception {

        UploadedFile archivo = event.getFile();
        if (archivo != null) {
            // Convertir el archivo a un array de bytes
            InputStream inputStream = archivo.getInputStream();
            byte[] contenidoBytes = inputStream.readAllBytes();
            this.archivoDTO.setNombreArchivo(archivo.getFileName());
            this.archivoDTO.setArchivo(contenidoBytes);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, archivo.getFileName(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            throw new TramiteException("El archivo está vacío");
            }
        }
        

    
    public void guardarArchivo(int nroTramite, int codTramiteDocumentacion) throws Exception{
        try{
            if(this.banderaCarga){
                controladorCompletarDocumentacion.guardarArchivo(this.nroTramiteExtends, codTramiteDocumentacion, this.archivoDTO);
                this.banderaCarga=false;
            }else{
                controladorCompletarDocumentacion.guardarArchivo(nroTramite, codTramiteDocumentacion, this.archivoDTO);
            }
            
        }catch(TramiteException e) {
        Messages.create("").error().detail(e.getMessage()).add();
        }
    }
    
    
    public void cancelar(){
        this.archivoDTO.setNombreArchivo(null);
        this.archivoDTO.setArchivo(null);

    }
    
    
    public void descargarArchivo(int nroTramite, int codTramiteDocumentacion) throws Exception{
        DTOArchivo archivoDescargado = new DTOArchivo();
    try {
        if(banderaDescarga){
            archivoDescargado = controladorCompletarDocumentacion.descargarArchivo(nroTramiteExtends, codTramiteDocumentacion);
            this.banderaDescarga = false;
        }else{
            archivoDescargado = controladorCompletarDocumentacion.descargarArchivo(nroTramite, codTramiteDocumentacion);
        }
        
        if (archivoDescargado.getArchivo() != null) {
            // Configuración de la respuesta para la descarga
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.setResponseContentType("application/octet-stream");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + archivoDescargado.getNombreArchivo() + "\"");
            OutputStream output = externalContext.getResponseOutputStream();
            
            // Escribir el contenido del archivo en getExternalContextel flujo de salida
            output.write(archivoDescargado.getArchivo()); // Aquí asumo que getArchivo() devuelve un byte[]
            output.flush(); // Asegúrate de que todos los datos se envían al cliente
            context.responseComplete(); // Completa la respuesta
        } else {
            // Manejo de error si el archivo no está disponible
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Archivo no encontrado.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    } catch (Exception e) {
        // Manejo de excepciones
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }


    
     // Getters y Setters
    
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public DTOCompletarDoc getDtoCompletarDoc() {
        return dtoCompletarDoc;
    }

    public void setTramiteSeleccionado(DTOCompletarDoc dtoCompletarDoc) {
        this.dtoCompletarDoc = dtoCompletarDoc;
    }

    public List<DocumentacionIU> getDocumentos() {
        return documentos;
    }

    
    public ControladorCompletarDocumentacion getControladorCompletarDocumentacion() {
        return controladorCompletarDocumentacion;
    }

    public void setControladorCompletarDocumentacion(ControladorCompletarDocumentacion controladorCompletarDocumentacion) {
        this.controladorCompletarDocumentacion = controladorCompletarDocumentacion;
    }


    public DTOArchivo getArchivoDTO() {
        return archivoDTO;
    }

    public void setArchivoDTO(DTOArchivo archivoDTO) {
        this.archivoDTO = archivoDTO;
    }
    
    public int getNroTramiteExtends() {
        return nroTramiteExtends;
    }

    public void setNroTramiteExtends(int nroTramiteExtends) {
        this.nroTramiteExtends = nroTramiteExtends;
    }
    
       public boolean isBanderaDescarga() {
        return banderaDescarga;
    }

    public void setBanderaDescarga(boolean banderaDescarga) {
        this.banderaDescarga = banderaDescarga;
    }
    

    public boolean isBanderaCarga() {
        return banderaCarga;
    }

    public void setBanderaCarga(boolean banderaCarga) {
        this.banderaCarga = banderaCarga;
    }
 
}
    
