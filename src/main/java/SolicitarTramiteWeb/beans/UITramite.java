
package SolicitarTramiteWeb.beans;

import SolicitarTramiteWeb.dtos.DTODocumentacion;
import entidades.Documentacion;
import entidades.TramiteDocumentacion;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class UITramite {
    private String nombree;
    private String descripcionn;
    private int codigoo;
    private String descripcionDocumentacion;
    private String nombreDocumentacion;
    private float precioTipoTramite;
    private List<DTODocumentacion> DTOdocumentacion = new ArrayList<>();
    private String nombreApellidoConsultor;
    private int nroTramite;
    private Timestamp fechaHoraVencimientoDocumentacion;

    public Timestamp getFechaHoraVencimientoDocumentacion() {
        return fechaHoraVencimientoDocumentacion;
    }

    public void setFechaHoraVencimientoDocumentacion(Timestamp fechaHoraVencimientoDocumentacion) {
        this.fechaHoraVencimientoDocumentacion = fechaHoraVencimientoDocumentacion;
    }

    
    
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }


    public String getNombreApellidoConsultor() {
        return nombreApellidoConsultor;
    }

    public void setNombreApellidoConsultor(String nombreApellidoConsultor) {
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }

    
    public List<DTODocumentacion> getDTOdocumentacion() {
        return DTOdocumentacion;
    }

    public void setDTOdocumentacion(List<DTODocumentacion> DTOdocumentacion) {
        this.DTOdocumentacion = DTOdocumentacion;
    }

    public void addDocumentacion(DTODocumentacion doc){
        this.DTOdocumentacion.add(doc);
    }
    
    
    public String getNombreDocumentacion() {
        return nombreDocumentacion;
    }

    public void setNombreDocumentacion(String nombreDocumentacion) {
        this.nombreDocumentacion = nombreDocumentacion;
    }

    public String getDescripcionDocumentacion() {
        return descripcionDocumentacion;
    }

    public void setDescripcionDocumentacion(String descripcionDocumentacion) {
        this.descripcionDocumentacion = descripcionDocumentacion;
    }

    public float getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    public void setPrecioTipoTramite(float precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }
    
    public String getNombree() {
        return nombree;
    }

    public void setNombree(String nombree) {
        this.nombree = nombree;
    }

    public String getDescripcionn() {
        return descripcionn;
    }

    public void setDescripcionn(String descripcionn) {
        this.descripcionn = descripcionn;
    }

    public int getCodigoo() {
        return codigoo;
    }

    public void setCodigoo(int codigoo) {
        this.codigoo = codigoo;
    }

   
   

    
}
