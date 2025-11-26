package RC_2.dto;

public class TransicionDTO {
    private int codEstadoTramite;
    private String nombreEstadoTramite;
    private String observacion;
    //METODOS GET
    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }
    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }
    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    //METODOS SET
    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
