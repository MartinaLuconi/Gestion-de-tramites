 package RC_2.dto;
import java.util.ArrayList;
import java.util.List;


public class EstadoActualDTO {

    private String nombreEstadoTramiteActual;
    private List<DestinosPosiblesDTO> destinosPosiblesList = new ArrayList<>(); 

    // getters y setters
    public List<DestinosPosiblesDTO> getDestinosPosiblesList() {
        return destinosPosiblesList;
    }
    

    public void setDestinosPosiblesList(List<DestinosPosiblesDTO> destinosPosibles) {
        this.destinosPosiblesList = destinosPosibles;
    }

    public String getNombreEstadoTramiteActual() {
        return nombreEstadoTramiteActual;
    }

    public void setNombreEstadoTramiteActual(String nombreEstadoTramiteActual) {
        this.nombreEstadoTramiteActual = nombreEstadoTramiteActual;
    }  

    
}