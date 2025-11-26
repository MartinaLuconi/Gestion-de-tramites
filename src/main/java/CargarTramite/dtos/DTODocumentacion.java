
package CargarTramite.dtos;

import java.sql.Timestamp;
import java.util.Date;


public class DTODocumentacion {

    
    private Date fechaHoraEntrega;
    private String nombreDocumentacion;
    
    
    
    public Date getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Date fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public String getNombreDocumentacion() {
        return nombreDocumentacion;
    }

    public void setNombreDocumentacion(String nombreDocumentacion) {
        this.nombreDocumentacion = nombreDocumentacion;
    }
    

}
