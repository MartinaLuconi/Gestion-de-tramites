package entidades;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ListaPrecio extends Entidad {

    private int codListaPrecio;
    private Date fechaHoraBajaListaPrecio;
    private Date fechaHoraDesdeListaPrecio;
    private Date fechaHoraHastaListaPrecio;
    private List<DetallePrecioTipoTramite> detallePrecioTipoTramiteList = new ArrayList<>();

    // Constructor
    public ListaPrecio() {
    }

    public int getCodListaPrecio() {
        return codListaPrecio;
    }

    public void setCodListaPrecio(int codListaPrecio) {
        this.codListaPrecio = codListaPrecio;
    }

    public Date getFechaHoraBajaListaPrecio() {
        return fechaHoraBajaListaPrecio;
    }

    public void setFechaHoraBajaListaPrecio(Date fechaHoraBajaListaPrecio) {
        this.fechaHoraBajaListaPrecio = fechaHoraBajaListaPrecio;
    }

    public Date getFechaHoraDesdeListaPrecio() {
        return fechaHoraDesdeListaPrecio;
    }

    public void setFechaHoraDesdeListaPrecio(Date fechaHoraDesdeListaPrecio) {
        this.fechaHoraDesdeListaPrecio = fechaHoraDesdeListaPrecio;
    }

    public Date getFechaHoraHastaListaPrecio() {
        return fechaHoraHastaListaPrecio;
    }

    public void setFechaHoraHastaListaPrecio(Date fechaHoraHastaListaPrecio) {
        this.fechaHoraHastaListaPrecio = fechaHoraHastaListaPrecio;
    }

    public List<DetallePrecioTipoTramite> getDetallePrecioTipoTramiteList() {
        return detallePrecioTipoTramiteList;
    }

    public void setDetallePrecioTipoTramiteList(List<DetallePrecioTipoTramite> detallePrecioTipoTramiteList) {
        this.detallePrecioTipoTramiteList = detallePrecioTipoTramiteList;
    }
    public void addDetallePrecioTipoTramiteList(DetallePrecioTipoTramite detallePrecioTipoTramite) {
        detallePrecioTipoTramiteList.add(detallePrecioTipoTramite);
    }
}
