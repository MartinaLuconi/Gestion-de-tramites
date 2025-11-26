package CompletarDocumentacion.beans;

import CompletarDocumentacion.ControladorCompletarDocumentacion;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.sql.Timestamp;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.StreamedContent;

public class DocumentacionIU {
    private ControladorCompletarDocumentacion controladorCompletarDocumentacion = new ControladorCompletarDocumentacion();
    private int codTramiteDocumentacion;
    private String nombreDocumentacion;
    private Timestamp fechaHoraEntrega;
    private UploadedFile archivo;
    private String nombre;
    private int nroTramite;
    private StreamedContent fileD; // Para almacenar el contenido del archivo
    private Timestamp fechaHoraFinEntregaDocumentacion;

    
    
    
    
    
    //getters and setters
    public ControladorCompletarDocumentacion getControladorCompletarDocumentacion() {
        return controladorCompletarDocumentacion;
    }

    public void setControladorCompletarDocumentacion(ControladorCompletarDocumentacion controladorCompletarDocumentacion) {
        this.controladorCompletarDocumentacion = controladorCompletarDocumentacion;
    }

    public StreamedContent getFileD() {
        return fileD;
    }

    public void setFileD(StreamedContent fileD) {
        this.fileD = fileD;
    }

    public Timestamp getFechaHoraFinEntregaDocumentacion() {
        return fechaHoraFinEntregaDocumentacion;
    }

    public void setFechaHoraFinEntregaDocumentacion(Timestamp fechaHoraFinEntregaDocumentacion) {
        this.fechaHoraFinEntregaDocumentacion = fechaHoraFinEntregaDocumentacion;
    }
    
    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getCodTramiteDocumentacion() {
        return codTramiteDocumentacion;
    }

    public void setCodTramiteDocumentacion(int codTramiteDocumentacion) {
        this.codTramiteDocumentacion = codTramiteDocumentacion;
    }

    public String getNombreDocumentacion() {
        return nombreDocumentacion;
    }

    public void setNombreDocumentacion(String nombreDocumentacion) {
        this.nombreDocumentacion = nombreDocumentacion;
    }

    public Timestamp getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Timestamp fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public void upload() {
        if (archivo != null) {
            FacesMessage message = new FacesMessage("Successful", archivo.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

//    public void handleFileUpload(FileUploadEvent event) {
//        FacesMessage message = new FacesMessage("Se cargó", event.getFile().getFileName() + " Peso (KB): " + event.getFile().getSize() / 1024f);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        System.out.println(event.getFile().getFileName());
//
//        UploadedFile archivo = event.getFile();
//
//        if (getCodTramiteDocumentacion() == codTramiteDocumentacion) {
//            if (archivo != null) {
//                try {
//                    InputStream inputStream = archivo.getInputStream();
//                    byte[] sourceBytes = IOUtils.toByteArray(inputStream);
//                    String encodedString = Base64.getEncoder().encodeToString(sourceBytes);
//                    String nombre = archivo.getFileName();
//
//                    controladorCompletarDocumentacion.actualizarDocumentacion(nroTramite, codTramiteDocumentacion, encodedString, nombre);
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo subido exitosamente", null));
//
//                } catch (Exception e) {
//                    Logger.getLogger(DocumentacionIU.class.getName()).log(Level.SEVERE, null, e);
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir el archivo", e.getMessage()));
//                }
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se seleccionó ningún archivo", null));
//            }
//        }
//    }
//
//    public StreamedContent getFileD(int codTramiteDocumentacion) {
//    Logger.getLogger(DocumentacionIU.class.getName()).log(Level.INFO, "Intentando descargar el documento: " + codTramiteDocumentacion);
//    
//    String base64Data = controladorCompletarDocumentacion.obtenerDocumentacion(codTramiteDocumentacion);
//    
//    if (base64Data != null) {
//        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
//        InputStream stream = new ByteArrayInputStream(decodedBytes);
//        
//        // Lógica para determinar el nombre y tipo de archivo
//        String nombreArchivo = "documento_" + codTramiteDocumentacion + ".pdf"; // Cambia según el tipo de archivo
//        String contentType = "application/octet-stream"; // Cambia según el tipo de archivo
//
//        return DefaultStreamedContent.builder()
//                .name(nombreArchivo)
//                .contentType(contentType)
//                .stream(() -> stream)
//                .build();
//    } else {
//        Logger.getLogger(DocumentacionIU.class.getName()).log(Level.SEVERE, "No se encontró el documento con código: " + codTramiteDocumentacion);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al descargar el archivo", "No se encontró el documento."));
//        return null;
//    }
//}



}
