package entidades;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Tramite extends Entidad {

    
    private int nroTramite;
    private float precioTramite;
    private Timestamp fechaHoraCargaTramite;
    private Timestamp fechaHoraVencimientoDocumentacion;
    private Timestamp fechaHoraFinEntregaDocumentacion;
    private Timestamp fechaHoraFinTramite;
    private Timestamp fechaHoraBajaTramite;
    private Consultor consultor;
    private Cliente cliente;
    private List<TramiteDocumentacion> tramiteDocumentacionList = new ArrayList<>();
    private TipoTramite tipoTramite;
    private EstadoTramite estadoTramite;
    private List<TramiteEstado> tramiteEstadoList = new ArrayList<>();
    private Version version;


        
    public Tramite(){}
    
    
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public float getPrecioTramite() {
        return precioTramite;
    }

    public void setPrecioTramite(float precioTramite) {
        this.precioTramite = precioTramite;
    }

    public Timestamp getFechaHoraVencimientoDocumentacion() {
        return fechaHoraVencimientoDocumentacion;
    }

    public void setFechaHoraVencimientoDocumentacion(Timestamp fechaHoraVencimientoDocumentacion) {
        this.fechaHoraVencimientoDocumentacion = fechaHoraVencimientoDocumentacion;
    }

    public Timestamp getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }

    public void setFechaHoraCargaTramite(Timestamp fechaHoraCargaTramite) {
        this.fechaHoraCargaTramite = fechaHoraCargaTramite;
    }

    public Timestamp getFechaHoraFinEntregaDocumentacion() {
        return fechaHoraFinEntregaDocumentacion;
    }

    public void setFechaHoraFinEntregaDocumentacion(Timestamp fechaHoraFinEntregaDocumentacion) {
        this.fechaHoraFinEntregaDocumentacion = fechaHoraFinEntregaDocumentacion;
    }

    public Timestamp getFechaHoraFinTramite() {
        return fechaHoraFinTramite;
    }

    public void setFechaHoraFinTramite(Timestamp fechaHoraFinTramite) {
        this.fechaHoraFinTramite = fechaHoraFinTramite;
    }

    public Consultor getConsultor() {
        return consultor;
    }

    public void setConsultor(Consultor consultor) {
        this.consultor = consultor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<TramiteDocumentacion> getTramiteDocumentacionList() {
        return tramiteDocumentacionList;
    }

    public void setTramiteDocumentacionList(List<TramiteDocumentacion> tramiteDocumentacionList) {
        this.tramiteDocumentacionList = tramiteDocumentacionList;
    }

    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public EstadoTramite getEstadoTramite() {
        return estadoTramite;
    }

    public void setEstadoTramite(EstadoTramite estadoTramite) {
        this.estadoTramite = estadoTramite;
    }

    public List<TramiteEstado> getTramiteEstadoList() {
        return tramiteEstadoList;
    }

    public void setTramiteEstadoList(List<TramiteEstado> tramiteEstadoList) {
        this.tramiteEstadoList = tramiteEstadoList;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void addTramiteDocumentacionList(TramiteDocumentacion td){
        this.tramiteDocumentacionList.add(td);
    }
    
    public void addTramiteEstadoList(TramiteEstado te){
        this.tramiteEstadoList.add(te);
    }

    public Timestamp getFechaHoraBajaTramite() {
        return fechaHoraBajaTramite;
    }
    
    public void setFechaHoraBajaTramite(Timestamp fechaHoraBajaTramite) {
        this.fechaHoraBajaTramite = fechaHoraBajaTramite;
    }
    
}
