package SolicitarTramiteWeb.dtos;



import entidades.EstadoTramite;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DTOTramiteWeb  {
    private String nombreTipoTramite;
    private int codTipoTramite ;
    private String descripcionTipoTramite;
    private List<DTODocumentacion> documentacionList;
    private int nroTramite;
    private float precioTramite;
    private Timestamp fechaHoraCargaTramite;
    private Timestamp fechaHoraVencimientoDocumentacion;
    private Timestamp fechaHoraFinEntregaDocumentacion;
    private Timestamp fechaHoraFinTramite;
    private EstadoTramite estadoTramite;
    private String descripcionTipoTramiteWeb;
    private String nombreConsultor;
    
    
    public DTOTramiteWeb() {
        this.documentacionList = new ArrayList();
    }
    

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    
    public String getDescripcionTipoTramite() {
        return descripcionTipoTramite;
    }

    public void setDescripcionTipoTramite(String descripcionTipoTramite) {
        this.descripcionTipoTramite = descripcionTipoTramite;
    }
    
    public String getDescripcionTipoTramiteWeb() {
        return descripcionTipoTramiteWeb;
    }

    public void setDescripcionTipoTramiteWeb(String descripcionTipoTramiteWeb) {
        this.descripcionTipoTramiteWeb = descripcionTipoTramiteWeb;
    }

    public EstadoTramite getEstadoTramite() {
        return estadoTramite;
    }

    public void setEstadoTramite(EstadoTramite estadoTramite) {
        this.estadoTramite = estadoTramite;
    }
   

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

    public Timestamp getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }

    public void setFechaHoraCargaTramite(Timestamp fechaHoraCargaTramite) {
        this.fechaHoraCargaTramite = fechaHoraCargaTramite;
    }

    public Timestamp getFechaHoraVencimientoDocumentacion() {
        return fechaHoraVencimientoDocumentacion;
    }

    public void setFechaHoraVencimientoDocumentacion(Timestamp fechaHoraVencimientoDocumentacion) {
        this.fechaHoraVencimientoDocumentacion = fechaHoraVencimientoDocumentacion;
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
    
    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public List<DTODocumentacion> getDocumentacionList() {
        return documentacionList;
    }

    public void setDocumentacionList(List<DTODocumentacion> documentacionList) {
        this.documentacionList = documentacionList;
    }

      public void addDocumentacionList(DTODocumentacion documentacion) {
        this.documentacionList.add(documentacion);
    } 

    public String getNombreConsultor() {
        return nombreConsultor;
    }

    public void setNombreConsultor(String nombreConsultor) {
        this.nombreConsultor = nombreConsultor;
    }
      
      
      
}

