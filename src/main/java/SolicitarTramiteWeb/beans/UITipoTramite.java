
package SolicitarTramiteWeb.beans;

import entidades.Documentacion;
import java.util.List;


public class UITipoTramite {
    private String nombree;
    private String descripcionn;
    private int codigoo;
    private String descripcionDocumentacion;
    private float precioTipoTramite;
    

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
