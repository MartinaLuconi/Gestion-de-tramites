package RC_2.dto;

import java.sql.Timestamp;



public class dtoFiltros {

    private int estado;
    private Timestamp fechaDesde;
    private int legajoConsultor;
    private int nombreTipoTramite;
    //METODOS GET Y SET
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }


    public Timestamp getFechaDesde() {
        return fechaDesde;
    }
    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    public int getLegajoConsultor() {
        return legajoConsultor;
    }
    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }


    public int getNombreTipoTramite() {
        return nombreTipoTramite;
    }
    public void setNombreTipoTramite(int nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }
    
}